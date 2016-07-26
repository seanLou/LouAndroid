package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.WindowFeature;

import cn.louguanyang.carbon.view.RippleLayout;
import cn.louguanyang.louandroid.R;
import cn.louguanyang.louandroid.presenters.MainPresenter;
import cn.louguanyang.louandroid.views.IMainView;


@WindowFeature(Window.FEATURE_NO_TITLE)
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements IMainView {

    private RippleLayout customViewLayout;
    private RippleLayout uploadImageLayout;
    private RippleLayout weatherLayout;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findView();
        mainPresenter = new MainPresenter(this);
        setOriginRipleLayout();

        customViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.startCustomActivity();
            }
        });

        weatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.startJuheActivity();
            }
        });

        uploadImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.startUploadImageActivity();
            }
        });

    }


    private void findView() {
        customViewLayout = (RippleLayout) findViewById(R.id.itemCustomView);
        weatherLayout = (RippleLayout) findViewById(R.id.itemWeather);
        uploadImageLayout = (RippleLayout) findViewById(R.id.itemUploadImage);
    }

    private void setOriginRipleLayout() {
        mainPresenter.setOriginRiple(customViewLayout);
        mainPresenter.setOriginRiple(weatherLayout);
        mainPresenter.setOriginRiple(uploadImageLayout);
    }

    @Override
    public void startUploadImageActivity() {
        Intent intent = new Intent(getApplicationContext(), UploadImageActivity.class);
        startActivity(intent);
    }

    @Override
    public void startJuheActivity() {
        Intent intent = new Intent(getApplicationContext(), JuheActivity.class);
        startActivity(intent);
    }

    @Override
    public void startCustomActivity() {
        Toast.makeText(getApplicationContext(), "customViewLayout", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setOriginRiple(final RippleLayout layoutRipple) {
        layoutRipple.post(new Runnable() {

            @Override
            public void run() {
                View v = layoutRipple.getChildAt(0);
                layoutRipple.setxRippleOrigin(ViewHelper.getX(v) + v.getWidth() / 2);
                layoutRipple.setyRippleOrigin(ViewHelper.getY(v) + v.getHeight() / 2);
                layoutRipple.setRippleColor(Color.parseColor("#1E88E5"));
                layoutRipple.setRippleSpeed(30f);
            }
        });
    }
}
