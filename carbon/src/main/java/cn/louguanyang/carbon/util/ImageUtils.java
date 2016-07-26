package cn.louguanyang.carbon.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LouGuanyang on 2015/9/8.
 */
public class ImageUtils {
    private final static int DEFAULT_IMG_QUALITY = 80;
    private final static int DEFAULT_IN_SAMPLE_SIZE = 2;
    private static final String TAG = ImageUtils.class.getSimpleName();
    private static final String DIR_NAME = "image";
    private static String IMAGE_FORMAT = ".png";

    private static String getFileBasePath() {
        return Environment.getExternalStorageDirectory() + File.separator + DIR_NAME + File.separator;
    }

    public static void createFileBasePath() {
        Log.d(TAG, ("[" + DateUtils.nowTimestamp() + "] FileBasePath:" + ImageUtils.getFileBasePath()));
        File file = new File(ImageUtils.getFileBasePath());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getPNGImagePath() {
        String imagePath = getFileBasePath() + UUID.randomUUID().toString() + IMAGE_FORMAT;
        Log.d(TAG, "[" + DateUtils.nowTimestamp() + "] imagePath:" + imagePath);
        return imagePath;
    }

    private static String getCompressTargetPath(@NonNull String srcPath) {
        try {
            String[] split = srcPath.split(".");
            return split[0] + "-compress" + IMAGE_FORMAT;
        } catch (Exception e) {
            return getPNGImagePath();
        }
    }

    /**
     * 压缩图片,并且覆盖原图
     *
     * @param sourcePath 待压缩图片的路径
     */
    public static void compressAndCoverImage(@NonNull String sourcePath) {
        compressedIamge(sourcePath, null);
    }

    /**
     * 新建压缩图片,并且返回新的图片路径
     *
     * @param sourcePath 待压缩图片的路径
     * @return 压缩后图片的路径
     */
    public static String cpmpressAndNewImage(@NonNull String sourcePath) {
        try {
            String targetPath = ImageUtils.getCompressTargetPath(sourcePath);
            compressedIamge(sourcePath, targetPath);
            return targetPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩图片
     *
     * @param srcPath
     * @param targetPath
     */
    private static void compressedIamge(@NonNull String srcPath, @Nullable String targetPath) {
        try {
            if (TextUtils.isEmpty(targetPath)) {
                targetPath = srcPath;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(srcPath, options);
            options.inSampleSize = DEFAULT_IN_SAMPLE_SIZE;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);
            File file = new File(targetPath);
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, DEFAULT_IMG_QUALITY, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片到服务器
     *
     * @param targetUrl     服务器地址
     * @param imagePath     图片路径
     * @param param        ContractUUID,HouseUUID,or UserId
     * @return
     */
    public static String uploadImage(String targetUrl, String imagePath, Map<String,String> param) {
        String responseString = "";
        //FIXME:改用okhttp
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPostHC4 httpPost = new HttpPostHC4(targetUrl);
            File sourceFile = new File(imagePath);
            FileBody fileBody = new FileBody(sourceFile);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("image", fileBody);
            for (Map.Entry<String, String> entry : param.entrySet()) {
                builder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.TEXT_PLAIN));
            }
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity responseEntity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseString = EntityUtils.toString(responseEntity);
                } else {
                    responseString = "Error occurred! Http Status Code: " + statusCode;
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                responseString = e.toString();
            }
        }
        return responseString;
    }

    /**
     * 获取相册图片路径
     *
     * @param context 上下文
     * @param uri     相册图片的Uri
     * @return 图片路径
     */
    public static String getImagePathFromMedia(Context context, Uri uri) {
        String path = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(columnIndexOrThrow);
            cursor.close();
        }
        return path;
    }

}
