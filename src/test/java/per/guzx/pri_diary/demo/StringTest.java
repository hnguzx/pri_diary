package per.guzx.pri_diary.demo;

import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) {
        String demo1 = "123,456,789,";
        String[] demo2 = demo1.split(",");
        System.out.println(demo2[0]);

        String demo4 = demo1.substring(demo1.indexOf(",") + 1);
        System.out.println(demo4);
    }
}
