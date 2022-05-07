package com.manage.java;

import java.util.Objects;
import java.util.Scanner;

public class CommandForManage {

    //下面是指令库，要求不重复
    public final String[] Command = {
            "/help",                                                                    //帮助
            "/create student",                                                          //创建
            "/search student with",                                                     //查找
            "/set student",                                                             //修改
            "/delete student with",                                                     //删除
            "/show student"                                                             //浏览
    };
    //下面为帮助库，与指令库不同，帮助库仅作为提示消息
    public final String[] Help = {
            "/help",                                                                    //帮助
            "/create student (Honkasei|Kensyuusei)",                                    //创建
            "/search student with（Num.<student_num>|Name.<student_name>|Class.<student_year>_<student_class>)",         //查找
            "/set student (Name|Age|Add|Class|Score) with Num.<student_num>",           //修改
            "/delete student with Num.<student_num>",
            "/show student (Num|Name|Age|Add|Class|Score) with (up|down)"               //浏览
    };
    final Student[] students = new Student[100];                                        //学生库（默认 100 上限）
    int studentindex = 0;                                                               //库中学生人数

    //将字符串数组加上 Title 后左对齐显示
    public static void showInfo(String[][] input) {

        //加上 Title
        String[][] Infos = new String[input.length + 1][14];
        String[] Title = new String[]{"Num", "Name", "Age", "Address", "Class", "Math_Score", "English_Score", "Politics_Score", "Major_Score", "Total_Score", "Student_Kind", "Major", "SenSei", "Target"};
        Infos[0] = Title;
        System.arraycopy(input, 0, Infos, 1, input.length);


        int[] maxlenofcol = new int[14];                //这里找出每列最长数的长度保存按列存入 []maxlenofcol
        for (int i = 0; i < 14; i++) {
            maxlenofcol[i] = 0;
        }
        for (int j = 0; j < 14; j++) {
            for (String[] info : Infos) {
                if (info[j].length() > maxlenofcol[j]) {
                    maxlenofcol[j] = info[j].length();
                }
            }
        }

        //终于要显示了
        System.out.print("\n");
        for (int i = 0; i < Infos.length; i++) {
            for (int j = 0; j < 14; j++) {
                if (i != Infos.length - 1) {
                    System.out.print("\33[4m");
                }
                System.out.printf("|%s", Infos[i][j]);
                Showing.blank(maxlenofcol[j] + 1 - Infos[i][j].length());
                if (i != Infos.length - 1) {
                    System.out.print("\33[0m");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        //显示完毕
    }

    //输入学生学号返回索引，不存在返回-1
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

        int i;                                                                          //指令索引
        for (i = 0; i < Command.length; i++) {                                          //搜索
            //若输入的指令左边部分与指令库中指令相符
            if (input.regionMatches(0, Command[i], 0, Command[i].length())) {
                //则将左边部分切掉，把指令索引和右边切片传入 toCommand 函数
                input = input.substring(Command[i].length());
                String[] splitofcommand = input.split(" ");                       //以空格切分输入的命令
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
            case 4: {
                DeleteStudent(commandnum, command);
                break;
            }
            case 5: {
                ShowStudent(commandnum, command);
                break;
            }
        }
    }

    //第一个指令，显示帮助库
    public void ShowHelp() {

        int lineinpage = 9;                                                             //指定一页显示的最大行数
        if (Help.length == 0) {                                                         //当帮助库里不写东西时，显示无帮助
            System.out.print("\33[31;1mNo command!\33[0m\n\n");
        } else {
            //反之
            int totalpage = 1 + Help.length / (lineinpage + 1);                         //计算总页数
            for (int nowpage = 1; nowpage <= totalpage; nowpage++) {                    //从第一页至最后一页

                System.out.print("--------Help Page--------\n");                        //显示帮助头
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
                System.out.printf("--------Page %d--------\n\n", nowpage);              //显示帮助尾
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

            //当为本科生时，提示种类和输入学号
            System.out.print("\33[31;1mYou are creating a Honkasei! Enter Num first!\33[0m\n");
            System.out.print("Enter the Num of student: ");
            Scanner input = new Scanner(System.in);
            int num;

            //防止不法值
            try {
                num = input.nextInt();
            } catch (Exception e) {
                System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
                return;
            }

            //检测学号是否已存在
            if (searchIndexOf(num) != -1) {
                System.out.printf("\33[31;1mStudent Num.%d already exists, please change the num of student you want to create!\33[0m\n\n", num);
                return;
            }

            //不存在即创建
            students[studentindex] = new Student(num, "Honkasei");
            students[studentindex].setStudent();
            studentindex++;
        } else if (Objects.equals(splitofcommand[1], "Kensyuusei")) {

            //当为研究生时，提示种类和输入学号
            System.out.print("\33[31;1mYou are creating a Kensyuusei! Enter Num first!\33[0m\n");
            System.out.print("Enter the Num of student: ");
            Scanner input = new Scanner(System.in);
            int num;

            //防止不法值
            try {
                num = input.nextInt();
            } catch (Exception e) {
                System.out.print("\33[31;1mThis vaule is illegal!\33[0m\n");
                return;
            }

            //检测学号是否已存在
            if (searchIndexOf(num) != -1) {
                System.out.printf("\33[31;1mStudent Num.%d already exists, please change the num of student you want to create!\33[0m\n\n", num);
                return;
            }

            //不存在即创建
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

        //按照 /search student with（Num.<student_num>|Name.<student_name>|Class.<student_year>_<student_class>) 的描述
        //现在输入的部分应当满足 (Num.<student_num>|Name.<student_name>|Class.<student_year>_<student_class>) 的格式
        if (splitofcommand.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //接下来按 '.' 分开内容
        String[] split = splitofcommand[1].split("\\.");
        if (split.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //分析点前的字符串，分类处理
        if (Objects.equals(split[0], "Num")) {

            //当以学号查找时
            int num;

            //防止不法值
            try {
                num = Integer.parseInt(split[1]);
            } catch (Exception e) {
                System.out.printf("\33[31;1mStudent Num.%s doesn't exists, please check the num of student you want to search!\33[0m\n\n", split[1]);
                return;
            }

            //检测学号是否已存在
            if (searchIndexOf(num) == -1) {
                //不存在则提示
                System.out.printf("\33[31;1mStudent Num.%d doesn't exists, please check the num of student you want to search!\33[0m\n\n", num);
                return;
            }

            //至此显示即可
            String[][] Infos = new String[1][14];
            Infos[0] = students[searchIndexOf(num)].backString();
            CommandForManage.showInfo(Infos);

        } else if (Objects.equals(split[0], "Name")) {

            //当以姓名查找时
            //由于可能出现重名情况，准备收集所有满足的人的信息
            String[][] Infos = new String[studentindex][14];
            int infosindex = 0;
            for (int i = 0; i < studentindex; i++) {
                if (Objects.equals(students[i].name, split[1])) {
                    Infos[infosindex] = students[i].backString();
                    infosindex++;
                }
            }


            if (infosindex == 0) {
                //不存在则提示
                System.out.printf("\33[31;1mStudent Name.%s doesn't exists, please check the name of student you want to search!\33[0m\n\n", split[1]);
            } else {
                //反之显示即可
                CommandForManage.showInfo(Infos);
            }

        } else if (Objects.equals(split[0], "Class")) {

            //当以班级查找时
            int year, class_num;
            String[] kurasu = split[1].split("_");
            if (kurasu.length != 2) {
                //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
                System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
                System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
                return;
            }

            //防止不法值
            try {
                year = Integer.parseInt(kurasu[0]);
                class_num = Integer.parseInt(kurasu[1]);
            } catch (Exception e) {
                System.out.printf("\33[31;1mStudent Class.%s_%s doesn't exists, please check the class of student you want to search!\33[0m\n\n", kurasu[0], kurasu[1]);
                return;
            }

            //由于可能出现同班情况，准备收集所有满足的人的信息
            String[][] Infos = new String[studentindex][14];
            int infosindex = 0;
            for (int i = 0; i < studentindex; i++) {
                if (students[i].kurasu[0] == year && students[i].kurasu[1] == class_num) {
                    Infos[infosindex] = students[i].backString();
                    infosindex++;
                }
            }

            if (infosindex == 0) {
                //不存在则提示
                System.out.printf("\33[31;1mStudent Class.%d_%d doesn't exists, please check the class of student you want to search!\33[0m\n\n", year, class_num);
            } else {
                //反之显示即可
                CommandForManage.showInfo(Infos);
            }

        } else {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
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
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //提示结束
        System.out.print("\33[32;1mDone.\33[0m\n\n");
    }

    //第五个指令，删除学生
    public void DeleteStudent(int commandnum, String[] splitofcommand) {

        //按照 /delete student with Num.<student_num> 的描述
        //现在输入的部分应当满足 Num.<student_num> 的格式
        if (splitofcommand.length != 2) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //接下来按 '.' 分开内容
        String[] split = splitofcommand[1].split("\\.");
        if (split.length != 2 || !Showing.isNumeric(split[1])) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //分析点前的字符串，分类处理
        if (Objects.equals(split[0], "Num")) {

            //当以学号删除时
            int num;

            //防止不法值
            try {
                num = Integer.parseInt(split[1]);
            } catch (Exception e) {
                System.out.printf("\33[31;1mStudent Num.%s doesn't exists, please check the num of student you want to search!\33[0m\n\n", split[1]);
                return;
            }

            //检测学号是否存在
            int indexofstudent = searchIndexOf(num);
            if (indexofstudent == -1) {
                //不存在则提示
                System.out.printf("\33[31;1mStudent Num.%d doesn't exists, please check the name of student you want to search!\33[0m\n\n", num);
                return;
            }

            //至此学生学号存在，再判断学生库人数（人数为零时已被上面过滤）
            if (studentindex == indexofstudent + 1) {
                //当搜寻的正好是库中最后一个（包含库中只有一个学生）时
                students[indexofstudent] = null;
            } else {
                //这里库中人数 >= 2，且搜寻的不是库中最后一个
                //那就把最后一个替换至要删的那个
                students[indexofstudent] = students[studentindex - 1];
            }
            //最后减少人数
            studentindex--;
        } else {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
        }
    }

    //第六个指令，依据 学号、姓名、年龄、地址、班级或成绩 升序或降序 显示
    public void ShowStudent(int commandnum, String[] splitofcommand) {

        //根据 /show student (Num|Name|Age|Add|Class|Score) with (up|down) 的描述
        //现在输入的部分应当满足 (Num|Name|Age|Add|Class|Score) with (up|down) 的格式
        if (splitofcommand.length != 4 || !Objects.equals(splitofcommand[2], "with") || !(Objects.equals(splitofcommand[3], "up") || splitofcommand[3].equals("down"))) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //把信息全部导出至 Infos
        String[][] Infos = new String[studentindex][14];
        if (studentindex == 0) {
            System.out.print("\33[31;1mThere's no student recorded!\33[0m\n\n");
            return;
        } else {

            for (int i = 0; i < studentindex; i++) {
                Infos[i] = students[i].backString();
            }
        }

        //准备分配并排序
        String[] Match = {"Num", "Name", "Age", "Add", "Class", "Score"};
        String[] Lesson = {"Math", "English", "Politics", "Major", "Total"};

        boolean matched = false;
        for (int i = 0; i <= 5; i++) {
            if (Objects.equals(splitofcommand[1], Match[i])) {
                matched = true;
                if (i != 5) {
                    //当不以 Score 排序时
                    //进行排序
                    Showing.sortIn(Infos, i, splitofcommand[3]);
                } else {
                    //当以 Score 排序时
                    //还需选择
                    System.out.print("Please enter the lesson name (Math|English|Politics|Major|Total) of the score you want to query: ");
                    Scanner input = new Scanner(System.in);
                    String lesson = input.nextLine();

                    boolean lesson_matched = false;
                    for (int j = 0; j < Lesson.length; j++) {
                        if (Objects.equals(lesson, Lesson[j])) {
                            lesson_matched = true;
                            //至此进行排序
                            Showing.sortIn(Infos, 5 + j, splitofcommand[3]);
                        }
                    }

                    if (!lesson_matched) {
                        //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
                        System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
                        System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
                        return;
                    }
                }
            }
        }

        if (!matched) {
            //不满足格式直接 illegal 处理，再把对应的帮助怼到用户脸上
            System.out.print("\33[31;1mThis command is illegal!\33[0m\n");
            System.out.print("Check by this: \33[31;1m" + Help[commandnum] + "\33[0m\n\n");
            return;
        }

        //至此各项排序完毕，输出即可
        CommandForManage.showInfo(Infos);

    }
}