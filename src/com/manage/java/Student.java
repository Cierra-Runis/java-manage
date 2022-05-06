package com.manage.java;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Student {

    int num;
    String name;
    int age;
    String[] address = new String[4];
    int[] kurasu = new int[2];
    double[] score = new double[4];
    String kind;

    //本科生
    String senkou;

    //研究生
    String sensei;
    String target;

    public Student(int num, String kind) {
        this.num = num;
        this.kind = kind;
        if (Objects.equals(kind, "Honkasei")) {
            this.sensei = "NULL";
            this.target = "NULL";
        } else if (Objects.equals(kind, "Kensyuusei")) {
            this.senkou = "NULL";
        }
    }

    public void setName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Name of student:");
        this.name = input.nextLine();
    }

    public void setAge() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Age of student:");
        this.age = input.nextInt();
    }

    public void setAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Province where the student from:");
        this.address[0] = input.nextLine();
        System.out.print("Please enter the City where the student from:");
        this.address[1] = input.nextLine();
        System.out.print("Please enter the Street where the student from:");
        this.address[2] = input.nextLine();
        System.out.print("Please enter the House Number where the student from:");
        this.address[3] = input.nextLine();
    }

    public void setKurasu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Year when the student entrance:");
        this.kurasu[0] = input.nextInt();
        System.out.print("Please enter the Class of student:");
        this.kurasu[1] = input.nextInt();
    }

    public void setScore() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Math Score of student:");
        this.score[0] = input.nextDouble();
        System.out.print("Please enter the English Score of student:");
        this.score[1] = input.nextDouble();
        System.out.print("Please enter the Politics Score of student:");
        this.score[2] = input.nextDouble();
        System.out.print("Please enter the Major Score of student:");
        this.score[3] = input.nextDouble();
    }

    //特殊
    public void setSenkou() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Major of student:");
        this.senkou = input.nextLine();
    }

    //特殊
    public void setSensei() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the SenSei of student:");
        this.sensei = input.nextLine();
    }

    public void setTarget() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Target of student:");
        this.target = input.nextLine();
    }

    public void setStudent() {
        setName();
        setAge();
        setAddress();
        setKurasu();
        setScore();

        if (Objects.equals(kind, "Honkasei")) {
            setSenkou();
        } else if (Objects.equals(kind, "Kensyuusei")) {
            setSensei();
            setTarget();
        }

        System.out.print("Done.\n\n");
    }

    public String toString() {
        return "Student{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + Arrays.toString(address) +
                ", kurasu=" + Arrays.toString(kurasu) +
                ", score=" + Arrays.toString(score) +
                ", kind='" + kind + '\'' +
                ", senkou='" + senkou + '\'' +
                ", sensei='" + sensei + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
