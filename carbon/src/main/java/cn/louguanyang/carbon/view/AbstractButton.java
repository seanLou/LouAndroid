package cn.louguanyang.carbon.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import cn.louguanyang.carbon.R;
import cn.louguanyang.carbon.util.SystemUtils;

import static cn.louguanyang.carbon.spec.Constans.DEFAULT_FLOAT_VALUE;

/**
 * Created by louguanyang on 15/12/19.
 */
public abstract class AbstractButton extends BaseView {

    int minWidth;
    int minHeight;
    int backgroundResource;
    float rippleSpeed = 12f;
    int rippleSize = 3;
    Integer rippleColor;
    View.OnClickListener onClickListener;
    /**
     * 如果按钮有动画,响应事件在动画加载完成后触发
     */
    boolean clickAfterRipple = true;
    int backgroundColor = Color.parseColor("#1E88E5");
    TextView textButton;

    public AbstractButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefaultProperties();
        clickAfterRipple = attrs.getAttributeBooleanValue(MATERIAL_DESIGN_XML, "animate", true);
        setAttributes(attrs);
        beforeBackground = backgroundColor;
        if (rippleColor == null) {
            rippleColor = initPressColor(this.backgroundColor);
        }
    }

    protected abstract void setAttributes(AttributeSet attrs);

    protected void setDefaultProperties() {
        setMinimumWidth(SystemUtils.dpToPx(minWidth, getResources()));
        setMinimumHeight(SystemUtils.dpToPx(minHeight, getResources()));
        setBackgroundResource(backgroundResource);
        setBackgroundColor(backgroundColor);
    }

    float x = DEFAULT_FLOAT_VALUE;
    float y = DEFAULT_FLOAT_VALUE;
    float radius = DEFAULT_FLOAT_VALUE;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        if (isEnabled()) {
            isLastTouch = true;
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                radius = getHeight() / rippleSize;
                x = event.getX();
                y = event.getY();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                radius = getHeight() / rippleSize;
                x = event.getX();
                y = event.getY();
                if (!((event.getX() <= getWidth() && event.getX() >= 0) && (event.getY() <= getHeight() && event.getY() >= 0))) {
                    isLastTouch = false;
                    x = DEFAULT_FLOAT_VALUE;
                    y = DEFAULT_FLOAT_VALUE;
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if ((event.getX() <= getWidth() && event.getX() >= 0) && (event.getY() <= getHeight() && event.getY() >= 0)) {
                    radius++;
                    if (onClickListener != null && !clickAfterRipple) {
                        onClickListener.onClick(this);
                    }
                } else {
                    isLastTouch = false;
                    x = DEFAULT_FLOAT_VALUE;
                    y = DEFAULT_FLOAT_VALUE;
                }
            } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                isLastTouch = false;
                x = DEFAULT_FLOAT_VALUE;
                y = DEFAULT_FLOAT_VALUE;
            }
        }
        return true;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (!gainFocus) {
            x = DEFAULT_FLOAT_VALUE;
            y = DEFAULT_FLOAT_VALUE;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void setOnClickListener(View.OnClickListener l) {
        onClickListener = l;
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        if (isEnabled()) {
            beforeBackground = color;
        }
        try {
            LayerDrawable layerDrawable = (LayerDrawable) getBackground();
            GradientDrawable shape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.background_shape);
            shape.setColor(backgroundColor);
            rippleColor = initPressColor(this.backgroundColor);
        } catch (Exception e) {

        }
    }

    public Bitmap createCircle() {
        Bitmap output = Bitmap.createBitmap(getWidth() - SystemUtils.dpToPx(6, getResources()),
                getHeight() - SystemUtils.dpToPx(7, getResources()), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(rippleColor);
        canvas.drawCircle(x, y, radius, paint);
        if (radius > getHeight() / rippleSize)
            radius += rippleSpeed;
        if (radius >= getWidth()) {
            x = DEFAULT_FLOAT_VALUE;
            y = DEFAULT_FLOAT_VALUE;
            radius = getHeight() / rippleSize;
            if (onClickListener != null && clickAfterRipple)
                onClickListener.onClick(this);
        }
        return output;
    }

    public float getRippleSpeed() {
        return rippleSpeed;
    }

    public void setRippleSpeed(float rippleSpeed) {
        this.rippleSpeed = rippleSpeed;
    }

    public void setText(String text) {
        textButton.setText(text);
    }

    public void setTextColor(int color) {
        textButton.setTextColor(color);
    }

    public TextView getTextButton() {
        return textButton;
    }

    public String getText() {
        return getTextButton().getText().toString();
    }
}
