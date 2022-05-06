package com.manage.java;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Showing {

    //显示指定空格
    public static void blank(int num) {
        for (int i = 0; i <= num; i++) {
            System.out.print(" ");
        }
    }

    //返回指定空格
    public static String blanks(int num) {
        StringBuilder blanks = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            blanks.append(" ");
        }
        return String.valueOf(blanks);
    }

    //判断一个字符串是否为数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        Matcher isNum = pattern.matcher(bigStr);
        return isNum.matches();
    }

}
