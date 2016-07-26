package cn.louguanyang.louandroid.biz;

import android.content.Context;
import android.os.Handler;

/**
 * Created by louguanyang on 16/1/13.
 */
public interface IUploadImageBiz {
    boolean cheackCamera(Context context);

    void createFileBasePath();

    void compressedAndUploadImage(Context context, String sourcePath, Handler handler);
}
