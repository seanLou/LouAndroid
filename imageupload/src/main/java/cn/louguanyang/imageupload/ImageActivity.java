package cn.louguanyang.imageupload;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageActivity extends Activity {
    private ImageView imageDownLoad;
    private RequestQueue mQueue;
    private BitmapCache bitmapCache;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        url = getIntent().getStringExtra("url");
        mQueue = Volley.newRequestQueue(this);
        bitmapCache = new BitmapCache();
        imageDownLoad = (ImageView) findViewById(R.id.imageDownLoad);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageLoader imageLoader = new ImageLoader(mQueue,bitmapCache);
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageDownLoad,R.mipmap.ic_launcher,R.mipmap.image_network_error);
        imageLoader.get(url, imageListener);
    }
}
