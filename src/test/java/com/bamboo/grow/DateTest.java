package com.bamboo.grow;

import com.bamboo.utils.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @author wls
 * @date 2019-04-19 16:13
 */
public class DateTest {
    private static Date date = new Date();

    @Test
    public void daysBetweenTwoDaysTest(){
        Date date = new Date();
        Date date1 = new Date();
        int i = DateUtil.daysBetweenTwoDays(date, date1);
        System.out.println(i);
    }

    @Test
    public void toDateStringTest(){
        String s = DateUtil.toDateString(date);
        System.out.println(s);
    }
    @Test
    public void isSameDayTest(){
        Boolean sameDay = DateUtil.isSameDay(date, new Date());
        System.out.println(sameDay);
    }
}
