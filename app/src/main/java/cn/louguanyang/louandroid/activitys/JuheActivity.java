package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import cn.louguanyang.carbon.util.PreferencesUtils;
import cn.louguanyang.louandroid.R;


public class JuheActivity extends Activity {

    private String[] resultStr = {"city", "PM2.5", "AQI", "quality", "PM10", "CO", "NO2", "O3", "SO2", "time"};
    private String[] resultDesc = {"城市: ", "PM2.5值:  ", "空气质量指数:   ", "空气质量:    ", "PM10:   ", "一氧化碳浓度: ", "二氧化氮浓度: ", "臭氧浓度:   ", "二氧化硫浓度: ", "time"};
    private String city = "hangzhou";
    private String TAG = JuheActivity.class.getSimpleName();
    private TextView tvContent;
    private TextView tvLastLoadTime;
    private final static String CITY_CONTENT = "cityContent";
    private final static String LAST_LOAD_TIME = "lastLoadTime";
    private String result;
    private String lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juhe);
        tvContent = (TextView) findViewById(R.id.tv_weather_content);
        tvLastLoadTime = (TextView) findViewById(R.id.tv_last_load_time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initDate();
    }

    private void initDate() {
        PreferencesUtils.getString(this, CITY_CONTENT);
//        Date now = new Timestamp(System.currentTimeMillis());

//        getWeather();
    }

}
