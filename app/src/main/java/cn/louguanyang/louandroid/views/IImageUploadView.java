package cn.louguanyang.louandroid.views;

/**
 * Created by louguanyang on 16/1/12.
 */
public interface IImageUploadView {
    void takePhoto();
    void openGallery();
    void showImage(String imageUrl);
    void showLargeImage(String imageUrl);
    void showToastMsg(String msg);
}
