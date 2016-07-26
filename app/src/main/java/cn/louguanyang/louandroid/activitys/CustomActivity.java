package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import cn.louguanyang.louandroid.R;


public class CustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom);
    }

}
