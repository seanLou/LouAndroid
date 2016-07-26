package cn.louguanyang.louandroid.presenters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import cn.louguanyang.carbon.exception.NoCameraPermissionException;
import cn.louguanyang.louandroid.R;
import cn.louguanyang.louandroid.biz.IPermissionBiz;
import cn.louguanyang.louandroid.biz.IUploadImageBiz;
import cn.louguanyang.louandroid.biz.impl.PermissionBizImpl;
import cn.louguanyang.louandroid.biz.impl.UploadImageBizImpl;
import cn.louguanyang.louandroid.views.IImageUploadView;


/**
 * Created by louguanyang on 16/1/15.
 */
public class UploadImagePresenter {
    private IImageUploadView mImageUploadView;
    private IUploadImageBiz uploadImageBiz;
    private IPermissionBiz permissionBiz;

    public UploadImagePresenter(IImageUploadView mImageUploadView) {
        this.mImageUploadView = mImageUploadView;
        uploadImageBiz = new UploadImageBizImpl();
        permissionBiz = new PermissionBizImpl();
    }

    /**
     * 检查是否有相机并创建文件夹
     *
     * @param context
     */
    public void checkCameraAndCreateDir(Context context) {
        if (uploadImageBiz.cheackCamera(context)) {
            uploadImageBiz.createFileBasePath();
        } else {
            mImageUploadView.showToastMsg(context.getString(R.string.no_camera_error_toast));
        }
    }

    public void startCamera(Activity activity) {
        try {
            permissionBiz.checkPermissionOfCamera(activity);
            mImageUploadView.takePhoto();
        } catch (NoCameraPermissionException e) {
            permissionBiz.requestPermissionOfCamera(activity);
        }
    }

    public void stratGallery(Activity activity) {
        //TODO:检查读取相册权限
        mImageUploadView.openGallery();
    }

    public void showImage(String imageUrl) {
        mImageUploadView.showImage(imageUrl);
    }

    public void showLargeImage(String imageUrl) {
        mImageUploadView.showLargeImage(imageUrl);
    }

    public void compressedAndUploadImage(Context context, String sourcePath, Handler handler) {
        uploadImageBiz.compressedAndUploadImage(context,sourcePath,handler);
    }
}
