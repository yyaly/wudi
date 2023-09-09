package yingcheng;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends password {
    static Scanner scanner = new Scanner(System.in);
    static String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt";
    public static void manager() {
        System.out.println("欢迎，尊敬的管理员，您需要什么服务？");
        System.out.println("密码管理(1)");
        System.out.println("内部人员管理(2)");
        System.out.println("退出(else)");
        int service=scanner.nextInt();
        if(service==1){
             password.password1(3);
            manager();
        }else if(service==2){
            Manager.userManager();
        }else {
            Main.main(null);
        }
    }

    
    


    private static void userManager() {
            System.out.println("请选择需要的功能");
        System.out.println("列出所有用户信息(1)");
        System.out.println("删除用户信息(2)");
        System.out.println("查询用户信息(3)");
        System.out.println("增加用户信息(4)");
        System.out.println("修改用户信息(5)");
        System.out.println("退出(else)");
        boolean find=true;
        int gl=scanner.nextInt();
        scanner.nextLine();
        if(gl==1){
            System.out.println("用户ID、用户名、用户注册时间、用户类型、用户手机号、用户邮箱:");
            try {
                FileReader fileReader = new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt");
            //创建 BufferedReader 对象
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String strLine = bufferedReader.readLine();
                while (strLine != null){
                    System.out.println(strLine);
                    strLine = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }catch (IOException e){
            System.out.println("文件不存在");
                  }
            System.out.println();
            Manager.userManager();
        }else if(gl==2){
           do{ System.out.println("请输入要删除用户信息的id");
            String ID=scanner.nextLine();
            int location= Integer.parseInt(ID);
            if(location!=1&&location!=2){
                System.out.println("找不到该用户，请重新输入");
                find=false;
            }else{
                System.out.println("是否确认删除(y/n)");
                String affirm=scanner.nextLine();
                if(affirm.equals("y")){
                    fileOperation1(location, filePath);

                    System.out.println("删除完毕");
                }
                Manager.userManager();
            }
           }while(!find);
        }else if(gl==3) {
            System.out.println("您选择什么进行查询(1用户ID,2用户名)");
            int way = scanner.nextInt();
            scanner.nextLine();
            if (way == 1) {
                do {
                    System.out.println("请输入要查询的用户id");
                    String ID = scanner.nextLine();
                    int location1 = Integer.parseInt(ID);
                    if (location1!=1&&location1!=2) {
                        System.out.println("找不到该用户，请重新输入");
                        find = false;
                    } else {
                        fileOperation2(location1);
                    }
                } while (!find);
            } else if (way == 2) {
                do {
                    System.out.println("请输入要查询的用户名");
                    String ID1 = scanner.nextLine();
                    int location2 =Login.check(ID1);
                    if (location2 == -1) {
                        System.out.println("找不到该用户，请重新输入");
                        find = false;
                    } else {
                        fileOperation2(location2);
                    }
                }while (!find) ;
            }
        } else if(gl==4){//用户ID、用户名、用户注册时间、用户类型（经理、前台）、用户手机号、用户邮箱；
add();
        }else if(gl==5){
System.out.println("请输入要修改用户的ID");
String id=scanner.nextLine();
int location= Integer.parseInt(id);
        System.out.println("请输入要修改的部分，用户类型(1)、用户手机号(2)、用户邮箱(3)，用户名(4)");
        int part=scanner.nextInt();
            scanner.nextLine();
            int location1=0;
        System.out.println("请输入修改内容");
        String content=scanner.nextLine();
        if(part==1){
           location1=4;
        }else if(part==2){
            location1=5;
            }else if(part==3){
            location1=6;
        }else if(part==4){
           location1=1;
        }
            List<String> fileContent = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int currentLineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (currentLineNumber == location) {
                        String[] words = line.split("\\s+");
                        words[location1] = content;
                        line = String.join(" ", words);
                    }
                    fileContent.add(line);
                    currentLineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : fileContent) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("修改完成");
           Manager.userManager();
        }else{
            Manager.manager();
        }

}

    private static void fileOperation2(int location1) {
        fileOperation3(location1, filePath);
        Manager.userManager();
    }

    static void fileOperation3(int location1, String filePath) {//查询
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == location1) {
                    System.out.println(line);
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("查询完毕");
    }

    static void fileOperation1(int location, String filePath) {//删除
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber !=location) {
                    fileContent.add(line);
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void add() {
        try {
            fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt",true);
        } catch (IOException e) {
            e.printStackTrace();

        }

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.newLine();
            String content;
        System.out.println("请输入增加用户Id");
        content=scanner.nextLine();
            bufferedWriter.write(content+ " ");
            repetition(content);
        System.out.println("请输入增加用户名");
            content=scanner.nextLine();
            bufferedWriter.write(content+ " ");
            repetition(content);
            bufferedWriter.write("123456789"+ " ");
        System.out.println("请输入增加用户注册时间");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入增加用户类型");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入增加用户手机号");
            content=scanner.nextLine();
            bufferedWriter.write(content+ " ");
            repetition(content);
        System.out.println("请输入增加用户邮箱");
            content=scanner.nextLine();
            bufferedWriter.write(content+ " ");
            repetition(content);
        System.out.println("增加成功");
            bufferedWriter.flush();
        Manager.userManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void repetition(String targetString) {
        List<String> fileContent = new ArrayList<>();
        boolean duplicateFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(targetString)) {
                    duplicateFound = true;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (duplicateFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (int i = 0; i < fileContent.size() - 1; i++) {
                    bw.write(fileContent.get(i));
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("发现重复");
            add();
        }
    }

}
