package cn.louguanyang.carbon.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static cn.louguanyang.carbon.spec.Constans.DEFAULT_FLOAT_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_INT_VALUE;

/**
 * Created by louguanyang on 15/12/24.
 */
public class RippleLayout extends BaseView {
    public final static float DEFAULT_RIPPLE_SIZE = 20f;


    int background;
    float rippleSpeed = DEFAULT_RIPPLE_SIZE;
    int rippleSize = 3;

    View.OnClickListener onClickListener;
    int backgroundColor = Color.parseColor("#FFFFFF");

    Integer rippleColor;
    Float xRippleOrigin;
    Float yRippleOrigin;

    public RippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    protected void setAttributes(AttributeSet attrs) {
        int backgroundColor = attrs.getAttributeResourceValue(ANDROID_XML, "background", DEFAULT_INT_VALUE);
        if (backgroundColor != DEFAULT_INT_VALUE) {
            setBackgroundColor(getResources().getColor(backgroundColor));
        } else {
            background = attrs.getAttributeIntValue(ANDROID_XML, "background", DEFAULT_INT_VALUE);
            if (background != DEFAULT_INT_VALUE) {
                setBackgroundColor(background);
            } else {
                setBackgroundColor(this.backgroundColor);
            }
        }
        int rippleColor = attrs.getAttributeResourceValue(MATERIAL_DESIGN_XML, "rippleColor", DEFAULT_INT_VALUE);
        if (rippleColor != DEFAULT_INT_VALUE) {
            setRippleColor(getResources().getColor(rippleColor));
        } else {
            rippleColor = attrs.getAttributeIntValue(MATERIAL_DESIGN_XML, "rippleColor", DEFAULT_INT_VALUE);
            if (background != DEFAULT_INT_VALUE) {
                setRippleColor(rippleColor);
            } else {
                setRippleColor(initPressColor(this.backgroundColor));
            }
        }
        rippleSpeed = attrs.getAttributeFloatValue(MATERIAL_DESIGN_XML, "rippleSpeed", DEFAULT_RIPPLE_SIZE);
    }

    @Override
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        if (isEnabled())
            beforeBackground = backgroundColor;
        super.setBackgroundColor(color);
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

    public Bitmap createCircle() {
        Bitmap output = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (rippleColor == null) {
            rippleColor = initPressColor(this.backgroundColor);
        }
        paint.setColor(rippleColor);
        x = (xRippleOrigin == null) ? x : xRippleOrigin;
        y = (yRippleOrigin == null) ? y : yRippleOrigin;
        canvas.drawCircle(x, y, radius, paint);
        if (radius > getHeight() / rippleSize)
            radius += rippleSpeed;
        if (radius >= getWidth()) {
            x = DEFAULT_FLOAT_VALUE;
            y = DEFAULT_FLOAT_VALUE;
            radius = getHeight() / rippleSize;
            if (onClickListener != null)
                onClickListener.onClick(this);
        }
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x != DEFAULT_FLOAT_VALUE) {
            Rect src = new Rect(0, 0, getWidth(), getHeight());
            Rect dst = new Rect(0, 0, getWidth(), getHeight());
            canvas.drawBitmap(createCircle(), src, dst, null);
            invalidate();
        }
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setRippleColor(Integer rippleColor) {
        this.rippleColor = rippleColor;
    }

    public void setRippleSpeed(float rippleSpeed) {
        this.rippleSpeed = rippleSpeed;
    }

    public void setxRippleOrigin(Float xRippleOrigin) {
        this.xRippleOrigin = xRippleOrigin;
    }

    public void setyRippleOrigin(Float yRippleOrigin) {
        this.yRippleOrigin = yRippleOrigin;
    }
}
