package nnu.net.foreginchinese.util;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DateUtil {
    private final static long nd = 1000 * 24 * 60 * 60;
    private final static  long nh = 1000 * 60 * 60;
    private final static  long nm = 1000 * 60;
    public static String getDatePoor(Date endDate, Date nowDate) {
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    public static long getDay(Date endDate, Date nowDate) {
        long diff = endDate.getTime() - nowDate.getTime();
        long day = diff / nd;
        return day;
    }
    public static long getDay(long endDate, long nowDate) {
        long diff = Math.abs(endDate - nowDate);
        long day = diff / nd;
        return day;
    }
}
