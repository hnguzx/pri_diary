package per.guzx.pri_diary.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前时间戳
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }
}
