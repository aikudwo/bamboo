package com.bamboo.utils;

import com.bamboo.Exception.ExceptionMessage;
import com.bamboo.Exception.MyException;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author wls
 * @date 2019-04-19 15:34
 */
public class DateUtil {

    private  static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private  static final SimpleDateFormat sdfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 计算两个日期之间相差的天数
     * @param smDate
     * @param bdDate
     * @return
     */
    public static int daysBetweenTwoDays(Date smDate,Date bdDate){
        smDate = parseYMD(formaterYMD(smDate));
        bdDate = parseYMD(formaterYMD(bdDate));
        long timeInMilli1 = getCalendarMills(smDate);
        long timeInMillis2 = getCalendarMills(bdDate);
        return Integer.parseInt(String.valueOf(timeInMillis2 - timeInMilli1));
    }

    /**
     * 将日期date 转成 yyyy-MM-dd HH:mm:dd 格式
     * @param date
     * @return
     */
    public static String toDateString(Date date){
        if(date == null) return null;
        return formaterYMDHMS(date);
    }

    /**
     * 判断两个是否是同一天
     * @param smDate
     * @param bdDate
     * @return
     */
    public static Boolean isSameDay(Date smDate,Date bdDate){
        return parseYMD(formaterYMD(smDate)).equals(parseYMD(formaterYMD(bdDate)));
    }


    public static String formaterYMD(Date date){
        return sdf.format(date);
    }

    public static String formaterYMDHMS(Date date){
        return sdfl.format(date);
    }

    public static Date parseYMD(String date){
        try {
            return sdf.parse(date);
        }catch (ParseException e){
            throw new MyException(ExceptionMessage.DATE_EXCEPTION,e);
        }
    }
    public static Date parseYMDHMS(String date){
        try {
            return sdfl.parse(date);
        }catch (ParseException e){
            throw new MyException(ExceptionMessage.DATE_EXCEPTION,e);
        }
    }

    public static Long getCalendarMills(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
}
