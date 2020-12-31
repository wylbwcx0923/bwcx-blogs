package com.bwcx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TestDouble {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("10.1","9.8","3.6","90.7","32.2","70.1");

        Collections.sort(list,(o1,o2)->Double.valueOf(o1)>Double.valueOf(o2)?1:-1);

        list.forEach(System.out::println);
    }

    /**
     * 得到某一天是这一月的第几周
     *
     * @param date
     * @return
     */
    public int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_MONTH);
        return week;
    }
}
