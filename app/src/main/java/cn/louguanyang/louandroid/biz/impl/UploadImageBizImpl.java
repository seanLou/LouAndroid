package cn.louguanyang.louandroid.biz.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

import cn.louguanyang.carbon.task.ImageUploadTask;
import cn.louguanyang.carbon.util.ImageUtils;
import cn.louguanyang.carbon.util.StringUtils;
import cn.louguanyang.louandroid.biz.IUploadImageBiz;

/**
 * Created by louguanyang on 16/1/13.
 */
public class UploadImageBizImpl implements IUploadImageBiz {

    private final static String BASE_REQUEST_URL = "http://10.0.2.2:8080/demo/";
    public final static String UPLOAD_IMAGE_URL = BASE_REQUEST_URL + "upload-image";

    @Override
    public boolean checkCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void createFileBasePath() {
        ImageUtils.createFileBasePath();
    }

    @Override
    public void compressedAndUploadImage(Context context, String sourcePath, Handler handler) {
        if(StringUtils.isEmpty(sourcePath)) {
            return;
        }
        String targetPath = ImageUtils.cpmpressAndNewImage(sourcePath);
        if(StringUtils.isEmpty(sourcePath)) {
            return;
        }
        new ImageUploadTask(context, handler).execute(UPLOAD_IMAGE_URL, targetPath, "123", "123");
    }
}
