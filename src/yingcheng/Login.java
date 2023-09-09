package yingcheng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Login extends Main {
    static Scanner scanner=new Scanner(System.in);
    public static void login(){
        boolean jin=true;
        int times=0;
        System.out.println("工作人员请用内部账号和密码登录");
        do {
            System.out.println("请输入账号:");
            String accountNumber = scanner.nextLine();
            System.out.println("请输入密码:");
            String password1=scanner.nextLine();
            if (check1(accountNumber)== check1(password1)&&check1(password1)==1) {
                System.out.println("用户登录成功！");
                User.user(ID(1));
            }else if(check(accountNumber)== check(password1)&&check(password1)==3) {
                System.out.println("管理员登录成功！");
                Manager.manager();
            }else if(check(accountNumber)== check(password1)&&check(password1)==2){
                System.out.println("前台登录成功！");
                Reception.reception();

            }else if(check(accountNumber)== check(password1)&&check(password1)==1){
                System.out.println("经理登录成功！");
                Boss.boss();
            }
            else{
                System.out.println("账号或密码错误，请重新输入");
                jin=false;
                times++;
            }
            if(times>5){
                System.out.println("错误次数过多，锁定账户！");
                Main.main(null);
            }
        }while(!jin);



    }

    private static int ID(int lineNumber) {
        String filePath ="D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt" ;
        String firstString=null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line= br.readLine();
            if(line != null) {
                String[] words = line.split("\\s+"); // 使用空格进行分割
                if (words.length > 0) {
                    firstString = words[0];
                    return Integer.parseInt(firstString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.requireNonNull(firstString).isEmpty()) {
            return -1; // 如果输入为空字符串
        }else{
            return Integer.parseInt(firstString);}
    }

    static int check(String accountNumber) {
        String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt";
        return checkLocation(accountNumber, filePath);
    }

    static int checkLocation(String accountNumber, String filePath) {//查询位置
        int lineNumber2=0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(accountNumber)) {
                    return lineNumber2+1;
                }
                lineNumber2++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static int check1(String accountNumber) {
        String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt";
        return checkLocation(accountNumber, filePath);
    }
}