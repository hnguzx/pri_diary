package per.guzx.priDiary.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTest {
    public static void main(String[] args) {
        /*String demo1 = "123,456,789,";
        String[] demo2 = demo1.split(",");
        System.out.println(demo2[0]);

        String demo4 = demo1.substring(demo1.indexOf(",") + 1);
        System.out.println(demo4);*/

        SimpleDateFormat format  = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String d = format.format(date);
        String timeStr = d.substring(0, 8) + "235959";
        System.out.println(timeStr);
    }
}
