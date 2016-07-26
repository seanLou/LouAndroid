package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;

import cn.louguanyang.carbon.spec.PermissionRequestCode;
import cn.louguanyang.carbon.util.ImageUtils;
import cn.louguanyang.carbon.view.RectangleButton;
import cn.louguanyang.louandroid.LouApplication;
import cn.louguanyang.louandroid.R;
import cn.louguanyang.louandroid.presenters.UploadImagePresenter;
import cn.louguanyang.louandroid.views.IImageUploadView;


public class UploadImageActivity extends Activity implements IImageUploadView, View.OnClickListener {
    private String TAG = UploadImageActivity.class.getSimpleName();
    private static final int PHOTO_REQUEST_TAKE_PHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private RectangleButton btnTakePhoto;
    private RectangleButton btnGallery;
    private ImageView imageView;
    private ImageLoader.ImageListener mImageListener;
    private UploadImagePresenter uploadImagePresenter;
    private final static String DEFAULT_IMAGE_URL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
    private String imageUrl = DEFAULT_IMAGE_URL;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_upload_image);
        findView();
        initData();
        setListener();
    }

    private void setListener() {
        btnTakePhoto.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    private void initData() {
        uploadImagePresenter = new UploadImagePresenter(this);
        mImageListener = ImageLoader.getImageListener(imageView, R.mipmap.ic_img_loading, R.mipmap.ic_img_error);
    }

    private void findView() {
        btnTakePhoto = (RectangleButton) findViewById(R.id.btn_take_photo);
        btnGallery = (RectangleButton) findViewById(R.id.btn_gallery);
        imageView = (ImageView) findViewById(R.id.loadImageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                uploadImagePresenter.startCamera(this);
                break;
            case R.id.btn_gallery:
                uploadImagePresenter.stratGallery(this);
                break;
            case R.id.loadImageView:
                uploadImagePresenter.showLargeImage(imageUrl);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionRequestCode.CAMERA_PERMISSION:
                uploadImagePresenter.startCamera(UploadImageActivity.this);
                break;
            case PermissionRequestCode.STORAGE_PERMISSION:
                uploadImagePresenter.startCamera(UploadImageActivity.this);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        uploadImagePresenter.checkCameraAndCreateDir(this);
        uploadImagePresenter.showImage(imageUrl);
    }

//    public final static int imageCategory = 4;//照片类型测试用
//    private String owerId = UUID.randomUUID().toString();
//    String requestURL = "http://luna.zufangbao.cn/luna/603436/uploadImage";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_REQUEST_TAKE_PHOTO) {
                Toast.makeText(this,"拍照",Toast.LENGTH_LONG).show();
                uploadImagePresenter.compressedAndUploadImage(this, photoPath, handler);
            } else if (requestCode == PHOTO_REQUEST_GALLERY) {
                if (data != null) {
                    String imagePath = ImageUtils.getImagePathFromMedia(this, data.getData());
                    uploadImagePresenter.compressedAndUploadImage(this, imagePath, handler);
                }
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // FIXME: 16/1/25 回调的图片地址
            Toast.makeText(UploadImageActivity.this,"TODO:回调的图片地址",Toast.LENGTH_LONG).show();
//            imageUrl = (String) msg.obj;
//            uploadImagePresenter.showImage(imageUrl);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoPath = ImageUtils.getPNGImagePath();// 指定调用相机拍照后照片的储存路径
        Uri photoUri = Uri.fromFile(new File(photoPath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        this.startActivityForResult(intent, PHOTO_REQUEST_TAKE_PHOTO);
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Override
    public void showImage(String imageUrl) {
        LouApplication.getInstance().getImageLoader().get(imageUrl, mImageListener);
    }

    @Override
    public void showLargeImage(String imageUrl) {
        Intent intent = new Intent(UploadImageActivity.this, ImageActivity.class);
        intent.putExtra("imageUrl", imageUrl);
        startActivity(intent);
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        finish();
    }

}
