package cn.louguanyang.imageupload;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * 创建文件目录地址
     **/
    private String fileBasePath = Environment.getExternalStorageDirectory() + File.separator + "TestUpload" + File.separator;
    private String imagePath;
    private static final int PHOTO_REQUEST_TAKE_PHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private Button btnTakePhoto;
    private Button btnGallery;
    private ImageView imageView;
    private RequestQueue mQueue;
    private BitmapCache bitmapCache;
    private ImageLoader imageLoader;
    private ImageLoader.ImageListener imageListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        btnGallery = (Button) findViewById(R.id.btn_gallery);
        imageView = (ImageView) findViewById(R.id.image_view);

        mQueue = Volley.newRequestQueue(this);
        bitmapCache = new BitmapCache();
        imageLoader = new ImageLoader(mQueue, bitmapCache);
        imageListener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.image_network_error);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stratGallery();
            }
        });
    }

    /**
     * 从相册选取照片
     */
    private void stratGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 拍照
     */
    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        imagePath = getPhotoFileName();
        File tempFile = new File(imagePath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, PHOTO_REQUEST_TAKE_PHOTO);
    }

    private String getPhotoFileName() {
        return fileBasePath + UUID.randomUUID().toString() + ".png";
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(), "抱歉! 您的手机貌似没有相机", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * 检查是否有相机并创建图片文件夹
     *
     * @return
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            createFile();
            return true;
        } else {
            return false;
        }
    }

    private void createFile() {
        File file = new File(fileBasePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
            Log.e(TAG, ("--------->" + file.mkdir()));
        }
    }

    public final static int imageCategory = 4;//照片类型测试用
    private String owerId = UUID.randomUUID().toString();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_REQUEST_TAKE_PHOTO) {
                ImageUtils.compressedAndCoveredImage(imagePath);
                new ImageUploadTask(this, handler).execute(BuildConfig.UPLOAD_BASE_URL, imagePath, owerId, String.valueOf(imageCategory));
            } else if (requestCode == PHOTO_REQUEST_GALLERY) {
                if (data != null) {
                    imagePath = ImageUtils.getImagePathFromMedia(data.getData(), this);
                    ImageUtils.compressedAndCoveredImage(imagePath);
                    new ImageUploadTask(this, handler).execute(BuildConfig.UPLOAD_BASE_URL, imagePath, owerId, String.valueOf(imageCategory));
                }
            }
        }
    }

    private String imgUrl = "http://aliyuntstest1.oss.aliyuncs.com/123.jpg";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ContractImg contractImg = (ContractImg) msg.obj;
            imgUrl = contractImg.getPath() + contractImg.getFileName();
            imageLoader.get(imgUrl, imageListener);
        }
    };

    public void loadImage(View view) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("url", imgUrl);
        startActivity(intent);
    }

    public void getWeather(View view) {
        doVollery();
    }

    private void doVollery() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://web.juhe.cn:8080/environment/air/pm", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                Log.v("result  ||", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("city", "hangzhou");
                params.put("key", "3ec5ecbe54df842762c2b43d21d62b65");
                return params;
            }
        };
        mQueue.add(stringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
