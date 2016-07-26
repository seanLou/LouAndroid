package cn.louguanyang.imageupload;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

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

/**
 * Created by LouGuanyang on 2015/9/8.
 */
public class ImageUtils {
    private final static int DEFAULT_IMG_QUILTY = 80;
    private final static int DEFAULT_IMG_INSAPLESIZE = 2;


    /**
     * 压缩并覆盖图片
     * @param srcPath
     */
    public static void compressedAndCoveredImage(String srcPath) {
        compressedIamge(srcPath, null);
    }

    /**
     * 压缩图片
     * @param srcPath
     * @param targetPath
     */
    public static void compressedIamge(String srcPath, String targetPath) {
        try {
            if (TextUtils.isEmpty(targetPath)) {
                targetPath = srcPath;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(srcPath, options);
            options.inSampleSize = DEFAULT_IMG_INSAPLESIZE;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);
            File file = new File(targetPath);
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, DEFAULT_IMG_QUILTY, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片到服务器
     * @param targetUrl 服务器地址
     * @param imagePath 图片路径
     * @param owerId ContractUUID,HouseUUID,or UserId
     * @param imageCategory 图片类型
     * @return
     */
    public static String uploadImage(String targetUrl, String imagePath, String owerId, String imageCategory) {
        String responseString = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPostHC4 httpPost = new HttpPostHC4(targetUrl);
            File sourceFile = new File(imagePath);
            FileBody fileBody = new FileBody(sourceFile);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("image", fileBody);
            builder.addPart("owerId", new StringBody(owerId, ContentType.TEXT_PLAIN));
            builder.addPart("imageCategory", new StringBody(imageCategory,ContentType.TEXT_PLAIN));
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
     * @param uri     相册图片的Uri
     * @param context 上下文
     * @return 图片路径
     */
    public static String getImagePathFromMedia(Uri uri, Context context) {
        String path = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        path = cursor.getString(columnIndexOrThrow);
        cursor.close();
        return path;
    }

}
