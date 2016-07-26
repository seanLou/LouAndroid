package cn.louguanyang.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import cn.louguanyang.carbon.utils.SystemUtils;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle == null)
            return;
        Object[] objArray = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objArray.length];
        for (int index = 0; index < objArray.length; index ++) {
            messages[index] = SmsMessage.createFromPdu((byte[]) objArray[index]);
            String mobile = messages[index].getDisplayOriginatingAddress();
            String messageBody = messages[index].getDisplayMessageBody();
            MyMessage message = new MyMessage(mobile,messageBody);
            sendToService(context,message);
            SystemUtils.sendSMS("5556",message.messageBody);
            Log.d("SMS---------->","SMS message" + message.toString());
            Toast.makeText(context,message.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void sendToService(final Context context, final MyMessage message) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest getVerifyCodeRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:8080/demo/message", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("message", message.toString());
                return params;
            }
        };
        mQueue.add(getVerifyCodeRequest);
    }
}
