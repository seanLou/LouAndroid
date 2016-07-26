package cn.louguanyang.carbon.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import java.util.Map;

import cn.louguanyang.carbon.net.request.LouStringRequest;


/**
 * Created by louguanyang on 16/3/30.
 */
public class VolleyUtils {

    public static String DEFAULT_TAG = VolleyUtils.class.getSimpleName();

    public static void startGetStringRequest(RequestQueue requestQueue, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params) {
        startGetStringRequestWithTAG(requestQueue, url, listener, errorListener, headers, params, null);
    }

    public static void startGetStringRequestWithTAG(RequestQueue requestQueue, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params, String tag) {
        startStringRequestWithTAG(requestQueue, Request.Method.GET, url, listener, errorListener, headers, params, tag);
    }

    public static void startPOSTStringRequest(RequestQueue requestQueue, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params) {
        startPOSTStringRequestWithTAG(requestQueue, url, listener, errorListener, headers, params, null);
    }

    public static void startPOSTStringRequestWithTAG(RequestQueue requestQueue, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params, String tag) {
        startStringRequestWithTAG(requestQueue, Request.Method.POST, url, listener, errorListener, headers, params, tag);
    }

    public static void startStringRequestWithTAG(RequestQueue requestQueue, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params, String tag) {
        LouStringRequest request = new LouStringRequest(method, url, listener, errorListener);
        request.setHeadersData(headers);
        request.setParamsData(params);
        addToRequestQueueWithTAG(requestQueue, request, tag);
    }

    private static <T> void addToRequestQueue(RequestQueue requestQueue, Request request) {
        addToRequestQueueWithTAG(requestQueue, request, null);
    }

    public static <T> void addToRequestQueueWithTAG(RequestQueue requestQueue, Request<T> request, @Nullable String tag) {
        if (StringUtils.isEmpty(tag)) {
            request.setTag(DEFAULT_TAG);
        } else {
            request.setTag(tag);
        }
        requestQueue.add(request);
    }

    public static void cancelPendingRequest(@NonNull RequestQueue requestQueue, @Nullable Object tag) {
        if (tag == null) {
            requestQueue.cancelAll(DEFAULT_TAG);
        } else {
            requestQueue.cancelAll(tag);
        }
    }
}
