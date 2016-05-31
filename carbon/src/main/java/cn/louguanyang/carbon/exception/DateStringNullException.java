package cn.louguanyang.carbon.exception;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by louguanyang on 16/5/31.
 */

public class DateStringNullException extends Exception implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DateStringNullException() {
    }

    protected DateStringNullException(Parcel in) {
    }

    public static final Parcelable.Creator<DateStringNullException> CREATOR = new Parcelable.Creator<DateStringNullException>() {
        @Override
        public DateStringNullException createFromParcel(Parcel source) {
            return new DateStringNullException(source);
        }

        @Override
        public DateStringNullException[] newArray(int size) {
            return new DateStringNullException[size];
        }
    };
}
