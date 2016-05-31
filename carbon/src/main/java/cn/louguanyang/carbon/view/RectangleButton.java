package cn.louguanyang.carbon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.louguanyang.carbon.R;
import cn.louguanyang.carbon.util.StringUtils;
import cn.louguanyang.carbon.util.SystemUtils;

import static cn.louguanyang.carbon.spec.Constans.DEFAULT_FLOAT_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_INT_VALUE;

/**
 * Created by louguanyang on 15/12/25.
 */
public class RectangleButton extends AbstractButton {

    public RectangleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefaultProperties();
    }

    @Override
    protected void setDefaultProperties() {
        minWidth = 80;
        minHeight = 36;
        backgroundResource = R.drawable.button_rectangle_background;
        super.setDefaultProperties();
    }

    @Override
    protected void setAttributes(AttributeSet attrs) {
        int bacgroundColor = attrs.getAttributeResourceValue(ANDROID_XML, "background", DEFAULT_INT_VALUE);
        if (bacgroundColor != DEFAULT_INT_VALUE) {
            setBackgroundColor(getResources().getColor(bacgroundColor));
        } else {
            backgroundResource = attrs.getAttributeIntValue(ANDROID_XML, "background", DEFAULT_INT_VALUE);
            if (backgroundResource != DEFAULT_INT_VALUE)
                setBackgroundColor(backgroundResource);
        }
        String text;
        int textResource = attrs.getAttributeResourceValue(ANDROID_XML, "text", DEFAULT_INT_VALUE);
        if (textResource != DEFAULT_INT_VALUE) {
            text = getResources().getString(textResource);
        } else {
            text = attrs.getAttributeValue(ANDROID_XML, "text");
        }
        if (!StringUtils.isEmpty(text)) {
            addTextButton(text);
            int textColor = attrs.getAttributeResourceValue(ANDROID_XML, "textColor", DEFAULT_INT_VALUE);
            if (textColor != DEFAULT_INT_VALUE) {
                textButton.setTextColor(textColor);
            } else {
                textColor = attrs.getAttributeIntValue(ANDROID_XML, "textColor", DEFAULT_INT_VALUE);
                if (textColor != DEFAULT_INT_VALUE)
                    textButton.setTextColor(textColor);
            }
            int[] array = {android.R.attr.textSize};
            TypedArray values = getContext().obtainStyledAttributes(attrs, array);
            float textSize = values.getDimension(0, DEFAULT_FLOAT_VALUE);
            values.recycle();
            if (textSize != DEFAULT_FLOAT_VALUE)
                textButton.setTextSize(textSize);
        }
        rippleSpeed = attrs.getAttributeFloatValue(MATERIAL_DESIGN_XML, "rippleSpeed", SystemUtils.dpToPx(6, getResources()));
    }

    private void addTextButton(String text) {
        textButton = new TextView(getContext());
        textButton.setText(text);
        textButton.setTextColor(Color.WHITE);
        textButton.setTypeface(null, Typeface.BOLD);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        params.setMargins(SystemUtils.dpToPx(5, getResources()), SystemUtils.dpToPx(5, getResources()), SystemUtils.dpToPx(5, getResources()), SystemUtils.dpToPx(5, getResources()));
        textButton.setLayoutParams(params);
        addView(textButton);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x != DEFAULT_FLOAT_VALUE) {
            Rect src = new Rect(0, 0, getWidth() - SystemUtils.dpToPx(6, getResources()), getHeight() - SystemUtils.dpToPx(7, getResources()));
            Rect dst = new Rect(SystemUtils.dpToPx(6, getResources()), SystemUtils.dpToPx(6, getResources()), getWidth() - SystemUtils.dpToPx(6, getResources()), getHeight() - SystemUtils.dpToPx(7, getResources()));
            canvas.drawBitmap(createCircle(), src, dst, null);
            invalidate();
        }
    }
}
