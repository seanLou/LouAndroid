package cn.louguanyang.carbon.exception;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by louguanyang on 16/5/31.
 */

public class DateParseException extends RuntimeException implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DateParseException() {
    }

    protected DateParseException(Parcel in) {
    }

    public static final Parcelable.Creator<DateParseException> CREATOR = new Parcelable.Creator<DateParseException>() {
        @Override
        public DateParseException createFromParcel(Parcel source) {
            return new DateParseException(source);
        }

        @Override
        public DateParseException[] newArray(int size) {
            return new DateParseException[size];
        }
    };
}
