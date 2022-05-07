package com.manage.java;

import java.util.Objects;
import java.util.Scanner;

public class Student {

    int num;                                                    //学号
    String name;                                                //姓名
    int age;                                                    //年龄
    String[] address = new String[4];                           //地址（省、市、街、号）
    int[] kurasu = new int[2];                                  //班级（学年、班号）
    double[] score = new double[4];                             //成绩（数学、英语、政治、专业）

    double total;                                               //总成绩

    String kind;                                                //学生种类（本科生、研究生）

    //本科生
    String senkou;                                              //专业

    //研究生
    String sensei;                                              //导师
    String target;                                              //目标

    //带参数（姓名、种类）的构造方法
    public Student(int num, String kind) {
        this.num = num;
        this.kind = kind;

        //根据种类对不属于该种学生的信息进行 "NULL" 赋值处理
        if (Objects.equals(kind, "Honkasei")) {
            this.sensei = "NULL";
            this.target = "NULL";
        } else if (Objects.equals(kind, "Kensyuusei")) {
            this.senkou = "NULL";
        }
    }

    //设置姓名
    public void setName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Name of student: ");
        this.name = input.nextLine();

        //防止空值
        if (Objects.equals(this.name, "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setName();
        }
    }

    //设置年龄
    public void setAge() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Age of student: ");

        //防止不法值
        try {
            this.age = input.nextInt();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setAge();
        }
    }

    //设置地址
    public void setAddress() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the Province where the student from: ");
        this.address[0] = input.nextLine();
        //防止空值
        if (Objects.equals(this.address[0], "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setAddress();
            return;
        }

        System.out.print("Please enter the City where the student from: ");
        this.address[1] = input.nextLine();
        //防止空值
        if (Objects.equals(this.address[1], "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setAddress();
            return;
        }

        System.out.print("Please enter the Street where the student from: ");
        this.address[2] = input.nextLine();
        //防止空值
        if (Objects.equals(this.address[2], "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setAddress();
            return;
        }

        System.out.print("Please enter the House Number where the student from: ");
        this.address[3] = input.nextLine();
        //防止空值
        if (Objects.equals(this.address[3], "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setAddress();
        }
    }

    //设置班级
    public void setKurasu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Year when the student entrance: ");

        //防止不法值
        try {
            this.kurasu[0] = input.nextInt();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setKurasu();
            return;
        }

        //防止人家不是现代人
        if (kurasu[0] < 1000 || kurasu[0] > 9999) {
            System.out.print("\33[31;1mThis vaule isn't a four-digit number!\33[0m\n");
            setKurasu();
            return;
        }

        System.out.print("Please enter the Class of student: ");
        //防止不法值
        try {
            this.kurasu[1] = input.nextInt();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setKurasu();
        }
    }

    //设置成绩
    public void setScore() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Math Score of student: ");
        //防止不法值
        try {
            this.score[0] = input.nextDouble();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setScore();
            return;
        }

        System.out.print("Please enter the English Score of student: ");
        //防止不法值
        try {
            this.score[1] = input.nextDouble();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setScore();
            return;
        }

        System.out.print("Please enter the Politics Score of student: ");
        //防止不法值
        try {
            this.score[2] = input.nextDouble();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setScore();
            return;
        }

        System.out.print("Please enter the Major Score of student: ");
        //防止不法值
        try {
            this.score[3] = input.nextDouble();
        } catch (Exception e) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setScore();
        }

        //统计总分
        this.total = this.score[0] + this.score[1] + this.score[2] + this.score[3];
    }

    //本科生专有
    //设置专业
    public void setSenkou() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Major of student: ");
        this.senkou = input.nextLine();
        //防止空值
        if (Objects.equals(this.senkou, "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setSenkou();
        }
    }

    //研究生专有
    //设置导师
    public void setSensei() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the SenSei of student: ");
        this.sensei = input.nextLine();
        //防止空值
        if (Objects.equals(this.sensei, "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setSenkou();
        }
    }

    //设置目标
    public void setTarget() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Target of student: ");
        this.target = input.nextLine();
        //防止空值
        if (Objects.equals(this.target, "")) {
            System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
            setSenkou();
        }
    }

    //全设定用
    public void setStudent() {
        setName();
        setAge();
        System.out.print("\n");
        setAddress();
        System.out.print("\n");
        setKurasu();
        System.out.print("\n");
        setScore();
        System.out.print("\n");

        //分类讨论
        if (Objects.equals(kind, "Honkasei")) {
            setSenkou();
        } else if (Objects.equals(kind, "Kensyuusei")) {
            setSensei();
            setTarget();
        }

        //提示结束
        System.out.print("\33[32;1mDone.\33[0m\n\n");
    }

    //返回信息串
    public String[] backString() {

        String[] info = new String[14];
        info[0] = String.valueOf(num);
        info[1] = name;
        info[2] = String.valueOf(age);

        info[3] = address[0] + ", " + address[1] + ", " + address[2] + ", " + address[3];

        info[4] = kurasu[0] + "_" + kurasu[1];

        info[5] = String.valueOf(score[0]);
        info[6] = String.valueOf(score[1]);
        info[7] = String.valueOf(score[2]);
        info[8] = String.valueOf(score[3]);

        info[9] = String.valueOf(total);

        info[10] = kind;

        info[11] = senkou;

        info[12] = sensei;
        info[13] = target;

        return info;
    }
}
