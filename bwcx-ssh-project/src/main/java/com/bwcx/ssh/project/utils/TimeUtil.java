package com.bwcx.ssh.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String date2String(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
