package cn.louguanyang.smsreceiver;

import java.util.Date;

/**
 * Created by louguanyang on 16/3/23.
 */
public class MyMessage {
    public String mobile;
    public String messageBody;
    public Date time;

    public MyMessage(String mobile, String messageBody) {
        this.mobile = mobile;
        this.messageBody = messageBody;
        this.time = new Date();
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "mobile='" + mobile + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", time=" + time +
                '}';
    }
}
