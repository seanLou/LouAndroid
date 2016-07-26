package cn.louguanyang.smsreceiver;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PermissionUtils.checkReceiveSMSPermission(this);
        } catch (NoPermissionException e) {
            PermissionUtils.requestReceiveSMSPermission(this,1000);
        }

        try {
            PermissionUtils.checkSendSMSPermission(this);
        } catch (NoPermissionException e) {
            PermissionUtils.requestSendSMSPermission(this,1001);
        }
    }

}
