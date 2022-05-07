package com.manage.java;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Showing {

    //显示指定空格
    public static void blank(int num) {
        for (int i = 0; i <= num; i++) {
            System.out.print(" ");
        }
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

    //冒泡排序
    // 借鉴于 https://blog.csdn.net/qq_30184977/article/details/99295865
    public static void sortIn(String[][] input, int index, String way){

        boolean change = false;
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length - i; j++) {
                //如果某人的 index 对应项字典排序高于后者，则交换
                if (input[j - 1][index].compareTo(input[j][index]) > 0) {
                    String[] temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }

        //按 index 对应项升序排序完毕，如果降序则反转
        if (Objects.equals(way, "down")) {
            for (int i = 0; i < input.length / 2; i++) {
                int j = input.length - i - 1;
                String[] temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }
    }

}
