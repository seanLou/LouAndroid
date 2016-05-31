package cn.louguanyang.carbon.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 StringRequest
 * Created by louguanyang on 16/3/31.
 */
public class LouStringRequest extends StringRequest {

    private static Map<String,String> mHeaders = new HashMap<>();

    static {
        mHeaders.put("Content-Type", "application/x-www-form-urlencoded");
    }

    public LouStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public LouStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }

    public void setHeadersData(Map<String, String> headers) {
        mHeaders.clear();
        if(null != headers && !headers.isEmpty()){
            mHeaders.put("Content-Type", "application/x-www-form-urlencoded");
            mHeaders.putAll(headers);
        }
    }

    private static Map<String,String> mParams = new HashMap<>();

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    public void setParamsData(Map<String, String> params) {
        mParams.clear();
        if(null != params && !params.isEmpty()) {
            mParams.putAll(params);
        }
    }
}
