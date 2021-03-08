package per.guzx.priDiary.tool;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class DateUtil {
    /**
     * 获取当前时间戳
     *
     * @return
     */
    public String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public String getDateStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    public Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
//        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
//        System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
//        System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }
}