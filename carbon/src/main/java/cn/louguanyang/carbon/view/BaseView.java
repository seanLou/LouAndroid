package cn.louguanyang.carbon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by louguanyang on 15/12/17.
 */
public class BaseView extends RelativeLayout {
    public final static String MATERIAL_DESIGN_XML = "http://schemas.android.com/apk/res-auto";
    public final static String ANDROID_XML = "http://schemas.android.com/apk/res/android";

    private final int disabledBackgroundColor = Color.parseColor("#E2E2E2");
    int beforeBackground;
    /** 是否是最后一次点击 **/
    public boolean isLastTouch = false;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(enabled)
            setBackgroundColor(beforeBackground);
        else
            setBackgroundColor(disabledBackgroundColor);
        invalidate();
    }
    boolean animation = false;

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
        animation = true;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        animation = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(animation)
            invalidate();
    }

    protected Integer initPressColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color ) & 0xFF;
        r = (r - 30 < 0) ? 0 : r - 30;
        g = (g - 30 < 0) ? 0 : g - 30;
        b = (b - 30 < 0) ? 0 : b - 30;
        return Color.rgb(r, g, b);
    }

}
