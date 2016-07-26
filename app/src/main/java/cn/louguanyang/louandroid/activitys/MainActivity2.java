package cn.louguanyang.louandroid.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import cn.louguanyang.carbon.view.BankCardEditText;
import cn.louguanyang.louandroid.LouApplication;
import cn.louguanyang.louandroid.R;

public class MainActivity2 extends Activity {

    private BankCardEditText bankCardEditText;
//    private RequestQueue mQueue;
    private EditText codeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findView();
//        mQueue = Volley.newRequestQueue(this);
    }

    private void findView() {
        bankCardEditText = (BankCardEditText) findViewById(R.id.bankCardEt);
        codeEt = (EditText) findViewById(R.id.codeEt);
    }

    public void showBankCardNumber(View v) {
        Toast.makeText(this, bankCardEditText.getInputText(), Toast.LENGTH_LONG).show();
    }

    public void getVerifyCode(View v) {
        StringRequest getVerifyCodeRequest = new StringRequest(Request.Method.POST, "http://192.168.1.240:8080/saturn/verify-code/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("mobile", "18268931252");
                return params;
            }
        };
        LouApplication.getInstance().getRequestQueue().add(getVerifyCodeRequest);
//        mQueue.add(getVerifyCodeRequest);
    }

    public void reward(View v) {
        final String code = codeEt.getText().toString();
        StringRequest rewardRequest = new StringRequest(Request.Method.POST, "http://192.168.1.240:8080/saturn/603436/reward", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("code", "圣诞节活动");
                return params;
            }
        };
//        mQueue.add(rewardRequest);
        LouApplication.getInstance().getRequestQueue().add(rewardRequest);
    }
}
