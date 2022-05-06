package com.manage.java;

import java.util.Objects;
import java.util.Scanner;

public class CommandForManage {
    String input;
    Student[] students = new Student[100];
    int studentindex = 0;

    //下面是指令库，要求不重复
    public final String[] Command = {
            "/help",
            "/create student",
            "/search student with",
            "/set student",
            "/delete student with",
            "/show student with"
    };

    //下面为帮助库，与指令库不同，帮助库仅作为提示消息
    public final String[] Help = {
            "/help",
            "/create student (Honkasei|Kensyuusei)",
            "/search student with（Num.<student_num>|Name.<student_name>|Class.<student_year>.<student_class>)",
            "/set student (Name|Age|Add|Class|Score) with Num.<student_num>",
            "/delete student with Num.<student_num>",
            "/show student (Num|Name|Age|Add|Class|Score) with (up|down)"
    };

    //使用含参数的构造方法时提供输入的指令
    CommandForManage(String input) {
        this.input = input;
    }

    //输入学生学号返回索引和种类，不存在返回-1
    public int searchIndexOf(int num) {
        for (int i = 0; i < studentindex; i++) {
            if (num == students[i].num) {
                return i;
            }
        }
        return -1;
    }

    //匹配指令
    public void match(String input) {

        int i;                                              //指令索引
        for (i = 0; i < Command.length; i++) {              //搜索
            //若输入的指令左边部分与指令库中指令相符
            if (input.regionMatches(0, Command[i], 0, Command[i].length())) {
                //则将左边部分切掉，把指令索引和右边切片传入 toCommand 函数
                input = input.substring(Command[i].length());
                String[] splitofcommand = input.split(" ");           //以空格切分输入的命令
                toCommand(i, splitofcommand);
                return;
            }
        }

        //否则提示无匹配指令
        if (i == Command.length) {
            System.out.print("\33[31;1mNo matched command!\33[0m\n\n");
        }
    }

    //根据传入的索引将索引和输入的指令分配到对应的处理部分
    public void toCommand(int commandnum, String[] command) {
        switch (commandnum) {
            case 0: {
                ShowHelp();
                break;
            }
            case 1: {
                CreateStudent(commandnum, command);
                break;
            }
            case 2: {
                SearchStudent(commandnum, command);
                break;
            }
            case 3: {
                SetStudent(commandnum, command);
                break;
            }
        }
    }

    //第一个指令，显示帮助库
    public void ShowHelp() {

        int lineinpage = 9;                                                 //指定一页显示的最大行数
        if (Help.length == 0) {                                             //当帮助库里不写东西时，显示无帮助
            System.out.print("\33[31;1mNo command!\33[0m\n\n");
        } else {
            //反之
            int totalpage = 1 + Help.length / (lineinpage + 1);             //计算总页数
            for (int nowpage = 1; nowpage <= totalpage; nowpage++) {        //从第一页至最后一页

                System.out.print("--------Help Page--------\n");            //显示帮助头
                //若当前页为最后一页
                if (nowpage != totalpage) {
                    //余数的限制下显示帮助
                    for (int line = 1; line <= lineinpage; line++) {
                        int num = lineinpage * (nowpage - 1) + line;
                        System.out.printf("%d. %s\n", num, Help[num - 1]);
                    }
                } else {
                    //否则在最大行数的限制下显示帮助
                    if (Help.length % lineinpage == 0) {
                        //当最后一页也是 9 行时，按 9 行输出
                        for (int line = 1; line <= lineinpage; line++) {
                            int num = lineinpage * (nowpage - 1) + line;
                            System.out.printf("%d. %s\n", num, Help[num - 1]);
                        }
                    } else {
                        //最后一页非 9 行时，按余数输出
                        for (int line = 1; line <= Help.length % lineinpage; line++) {
                            int num = lineinpage * (nowpage - 1) + line;
                            System.out.printf("%d. %s\n", num, Help[num - 1]);
                        }
                    }
                }
                System.out.printf("--------Page %d--------\n\n", nowpage);  //显示帮助尾
            }
        }
    }

    //第二个指令，创建学生
    public void CreateStudent(int commandnum, String[] splitofcommand) {

        //按照 /create student (Student|Kensyuusei) 的描述
        //现在输入的部分应当满足 (Student|Kensyuusei) 的格式
        if (splitofcommand.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //接下来检测学生种类
        if (Objects.equals(splitofcommand[1], "Honkasei")) {
            System.out.print("\33[31;1mYou are creating a Honkasei! Enter Num first!\33[0m\n");
            System.out.print("Enter the Num of student:");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            if (searchIndexOf(num) != -1) {
                System.out.printf("\33[31;1mStudent Num.%d already exists, please change the num of student you want to create!\33[0m\n\n", num);
                return;
            }
            students[studentindex] = new Student(num, "Honkasei");
            students[studentindex].setStudent();
            studentindex++;
        } else if (Objects.equals(splitofcommand[1], "Kensyuusei")) {
            System.out.print("\33[31;1mYou are creating a Kensyuusei! Enter Num first!\33[0m\n");
            System.out.print("Enter the Num of student:");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            if (searchIndexOf(num) != -1) {
                System.out.printf("\33[31;1mStudent Num.%d already exists, please change the num of student you want to create!\33[0m\n\n", num);
                return;
            }
            students[studentindex] = new Student(num, "Kensyuusei");
            students[studentindex].setStudent();
            studentindex++;
        } else {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.printf("\33[31;1mStudent kind \"%s\" doesn't exists!\33[0m\n", splitofcommand[1]);
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
        }
    }

    //第三个指令，依照 学号、姓名或班级 查找
    public void SearchStudent(int commandnum, String[] splitofcommand) {

        //按照 /search student with（Num.<student_num>|Name.<student_name>|Class.<student_year>.<student_class>) 的描述
        //现在输入的部分应当满足 (Num.<student_num>|Name.<student_name>|Class.<student_year>.<student_class>) 的格式
        if (splitofcommand.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //接下来按 . 分开内容
        String[] split = splitofcommand[1].split("\\.");
        if (split.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        if (Objects.equals(split[0], "Num") && Showing.isNumeric(split[1])) {
            int num = Integer.parseInt(split[1]);
            if (searchIndexOf(num) == -1) {
                System.out.printf("\33[31;1mStudent Num.%d doesn't exists, please check the name of student you want to search!\33[0m\n\n", num);
                return;
            }
            //TODO:这里要结合最后一个指令，这里暂用 toString() 顶替
            System.out.print(students[searchIndexOf(num)].toString() + "\n");
        }
    }

    //第四个指令，依据学号设定 姓名、年龄、地址、班级或成绩
    public void SetStudent(int commandnum, String[] splitofcommand) {

        //按照 /set student (Name|Age|Add|Class|Score) with Num.<student_num> 的描述
        //现在输入的部分应当满足 (Name|Age|Add|Class|Score) with Num.<student_num> 的格式
        if (splitofcommand.length != 4 || !Objects.equals(splitofcommand[2], "with")) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //接下来确认学号是否存在
        String[] split = splitofcommand[3].split("\\.");
        if (split.length != 2 || !Objects.equals(split[0], "Num")) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }
        int num = Integer.parseInt(split[1]);
        int indexofstudent = searchIndexOf(num);
        if (indexofstudent == -1) {
            System.out.printf("\33[31;1mStudent Num.%d doesn't exists, please check the name of student you want to search!\33[0m\n\n", num);
            return;
        }

        //至此就是根据学号改前面的项
        if (Objects.equals(splitofcommand[1], "Name")) {
            students[indexofstudent].setName();
        } else if (Objects.equals(splitofcommand[1], "Age")) {
            students[indexofstudent].setAge();
        } else if (Objects.equals(splitofcommand[1], "Add")) {
            students[indexofstudent].setAddress();
        } else if (Objects.equals(splitofcommand[1], "Class")) {
            students[indexofstudent].setKurasu();
        } else if (Objects.equals(splitofcommand[1], "Score")) {
            students[indexofstudent].setScore();
        } else {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.printf("\33[31;1m\"%s\" of student can't be find!\33[0m\n", splitofcommand[1]);
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }
        System.out.print("Done.\n\n");
    }
}
