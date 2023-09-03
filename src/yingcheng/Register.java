package yingcheng;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Register extends Main {
    static  String account;
    static String  password;
    static String mailBox;
    static String number;
    static int ID=1000;
    static Scanner scanner=new Scanner(System.in);

    public static void register(){

       boolean ru;
       do{
           System.out.println("请输入账号(不少于5个字符):");
       account =scanner.nextLine();
System.out.println("请输入邮箱地址");
mailBox =scanner.nextLine();
System.out.println("请输入手机号");
number=scanner.nextLine();
       System.out.println("请输入密码（不少于8个字符且为大小写字母和数字，标点符号组合）:");
      password=scanner.nextLine();
      System.out.println("请再次确认密码");
           String password1 = scanner.nextLine();
           if(!password1.equals(password)){
               System.out.println("密码不一致，请重新注册");
               register();
           }
           String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";
           Pattern regex = Pattern.compile(pattern);
           Matcher matcher = regex.matcher(password);
        if(account.length()<5||password.length()<8) {
            System.out.println("账号或密码输入错误！请重新输入");
            ru=false;
        }else if(!matcher.matches()){
            System.out.println("账号或密码输入错误！请重新输入");
            ru=false;
        }else{
            ru=true;
        }
       } while(!ru);
        ID++;
        String userID = String.valueOf(ID);
       System.out.println("注册成功！");//用户ID、用户名、密码、用户邮箱，用户手机号，用户级别（金牌用户、银牌用户、铜牌用户）、
        // 用户注册时间、用户累计消费总金额、用户累计消费次数；
        user.add(userID);
        user.add(account);
       user.add(password);
       user.add(mailBox);
        user.add(number);
        user.add("铜牌用户");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        user.add(formattedDateTime);
        user.add(String.valueOf(0));
        user.add(String.valueOf(0));
         Main.main(null);

    }


}
