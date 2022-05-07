package com.manage.java;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random seed = new Random();

        System.out.print("Enter command just like /help\n> ");      //提示
        String input = scanner.nextLine();                          //第一条命令
        CommandForManage command = new CommandForManage();          //创建一个 CommandForManage 的对象

        while (!input.equals("/exit")) {                            //进入循环，只要输入不是 /exit 就不结束
            command.match(input);                                   //利用 匹配指令 方法处理命令
            //提示及随机 tip
            System.out.print("Enter command just like " + command.Help[seed.nextInt(command.Help.length)] + "\n> ");
            input = scanner.nextLine();                             //再输入
        }

    }

}