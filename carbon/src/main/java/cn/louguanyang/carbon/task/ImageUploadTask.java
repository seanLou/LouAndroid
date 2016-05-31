package cn.louguanyang.carbon.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.louguanyang.carbon.util.ImageUtils;

/**
 * Created by LouGuanyang on 2015/9/15.
 */
public class ImageUploadTask extends AsyncTask<String, Integer, String> {
    private final static String TAG = ImageUploadTask.class.getSimpleName();
    private ProgressDialog progressDialog;
    private Context context;
    private Handler handler;

    public ImageUploadTask() {
        super();
    }

    public ImageUploadTask(Context context, Handler handler) {
        this();
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected String doInBackground(String... params) {
        String requestURL = params[0];
        String imagePath = params[1];
        String owenrId = params[2];
        String imageCategory = params[3];
        Map<String,String> param = new HashMap<>();
        param.put("ownerId", owenrId);
        return ImageUtils.uploadImage(requestURL, imagePath, param);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.e(TAG, "Response from server: " + result);
        progressDialog.dismiss();
        processResult(result);
        super.onPostExecute(result);
    }

    private void processResult(String result) {
//        try {
//            JSONObject resultJSONObject = JSONObject.parseObject(result);
//            String code = resultJSONObject.getString("code");
//            if ("0".equals(code)) {
//                Message msg = handler.obtainMessage();
//                String jsonObject = JSONObject.parseObject(result).getJSONObject("data").getString("imageResult");
////                ContractImg contractImg = JSON.parseObject(jsonObject, ContractImg.class);
////                msg.obj = contractImg;
//                String imgUrl = "http://aliyuntstest1.oss.aliyuncs.com/123.jpg";
//                msg.obj = imgUrl;
//                handler.sendMessage(msg);
//            } else {
//                Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
            Toast.makeText(context, "Result:" + result, Toast.LENGTH_LONG).show();
//        }
    }
}
