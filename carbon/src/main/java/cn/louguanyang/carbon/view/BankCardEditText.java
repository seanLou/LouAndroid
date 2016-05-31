package cn.louguanyang.carbon.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Created by LouGuanyang on 2015/9/18.
 */
public class BankCardEditText extends EditText {
    private static final int DEFAULT_INTERVAL = 4;
    private static final String DEFAULT_PLACE_HOLDER = " ";
    /**
     * 银行卡最大长度： 19 + 4个占位符
     */
    private static final int DEFAULT_MAX_LENGTH = 23;
    private int interval;
    private String placeHolder;
    private int maxLength;
    private CharSequence inputText = "";

    public BankCardEditText(Context context) {
        this(context, null);
    }

    public BankCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        interval = DEFAULT_INTERVAL;
        placeHolder = DEFAULT_PLACE_HOLDER;
        maxLength = DEFAULT_MAX_LENGTH;
        setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        setSingleLine();
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        addTextChangedListener(new BankCardTextWatcher());
    }

    private class BankCardTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            if (lengthBefore == lengthAfter) {
                return;
            } else {
                inputText = text.toString().replaceAll(placeHolder, "");
                formatInputText(inputText);
            }
        }
    }

    private void formatInputText(CharSequence text) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (i != 0 && i % interval == 0) {
                stringBuffer.append(placeHolder).append(text.charAt(i));
            } else {
                stringBuffer.append(text.charAt(i));
            }
        }
        BankCardEditText.this.setText(stringBuffer.toString());
        BankCardEditText.this.setSelection(stringBuffer.toString().length());
    }

    /**
     * 设置间距
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * 设置占位符
     * @param placeHolder
     */
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     * 设置可输入的最大长度
     * @param maxLength
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 获取输入结果
     * @return
     */
    public CharSequence getInputText() {
        return inputText;
    }

}
