package com.special.wheelview.util;

import java.util.Calendar;

/**
 * Created by on 2018/5/29.
 */

public class DateUtils {


    private static int mYear;
    private static int mMonth;

    public static int getYY(){
        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份

        return mYear;
    }

    public static int getMM(){
        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份

        return mMonth;
    }







}
