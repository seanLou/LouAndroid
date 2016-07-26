package cn.louguanyang.louandroid.presenters;


import cn.louguanyang.carbon.view.RippleLayout;
import cn.louguanyang.louandroid.views.IMainView;

/**
 * Created by louguanyang on 16/1/11.
 */
public class MainPresenter {
    private IMainView mMainView;

    public MainPresenter(IMainView mainView) {
        this.mMainView = mainView;
    }

    public void startUploadImageActivity() {
        mMainView.startUploadImageActivity();
    }

    public void startJuheActivity() {
        mMainView.startJuheActivity();
    }

    public void startCustomActivity() {
        mMainView.startCustomActivity();
    }

    public void setOriginRiple(RippleLayout rippleLayout) {
        mMainView.setOriginRiple(rippleLayout);
    }
}
