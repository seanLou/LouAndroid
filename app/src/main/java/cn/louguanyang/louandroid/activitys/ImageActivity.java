package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import cn.louguanyang.louandroid.LouApplication;
import cn.louguanyang.louandroid.R;

public class ImageActivity extends Activity {
    private ImageView imageDownLoad;
    private String imageUrl;

    private final static String DEFAULT_IMAGE_URL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image);
        imageUrl = getIntent().getStringExtra("imageUrl");
        if(imageUrl == null){
            imageUrl = DEFAULT_IMAGE_URL;
        }
        imageDownLoad = (ImageView) findViewById(R.id.imageDownLoad);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageDownLoad, R.mipmap.ic_img_loading, R.mipmap.ic_img_error);
        LouApplication.getInstance().getImageLoader().get(imageUrl, imageListener);
    }
}
