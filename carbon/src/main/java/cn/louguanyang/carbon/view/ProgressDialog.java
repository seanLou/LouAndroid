package cn.louguanyang.carbon.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import cn.louguanyang.carbon.R;
import cn.louguanyang.carbon.util.StringUtils;

/*
 * default value :
 * toShowText : false
 * showOverlay : true
 * cancelable : false
 */
public class ProgressDialog {
    private Context mContext;
    private Dialog dialog;
    private boolean toShowText = false;
    private String showedText;
    private boolean showOverlay = true;

    /*
     * 不显示文字
     */
    public ProgressDialog(Context context) {
        this(context, null);
    }

    /*
     * @param showedText 要显示的文字，最多3个文字，在资源文件中显示的是 加载中。若不显示文字，则传递null或""
     */
    public ProgressDialog(Context context, String showedText) {
        mContext = context;
        if (!StringUtils.isBlank(showedText)) {
            toShowText = true;
            this.showedText = showedText;
        }
        dialog = new Dialog(context, R.style.mask_dialog);
    }

    public void show() {
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_progress);
        window.setGravity(Gravity.CENTER);

        if (!showOverlay) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        int imageViewResId = R.id.iv_progress_big;
        if (toShowText) {
            imageViewResId = R.id.iv_progress_small;
            window.findViewById(R.id.rl_progress_small).setVisibility(View.VISIBLE);
            window.findViewById(R.id.rl_progress_big).setVisibility(View.GONE);
            TextView textView = (TextView) window.findViewById(R.id.tv_progress);
            textView.setText(showedText);
        }
        ImageView imageView = (ImageView) window.findViewById(imageViewResId);
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_progress_rotate);
        imageView.setAnimation(anim);
    }

    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    /*
     * @param showOverlay 是否显示透明的黑色背景，默认true
     */
    public void setShowOverlay(boolean showOverlay) {
        this.showOverlay = showOverlay;
    }

    public void cancel() {
        if (dialog != null) {
            dialog.cancel();
        }
    }
}

