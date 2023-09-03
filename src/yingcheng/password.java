package yingcheng;

import java.security.SecureRandom;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class password extends Main {
    static Scanner scanner=new Scanner(System.in );
    public static boolean password1(int ID) {
boolean back=false;
System.out.println("您需要什么服务？");
System.out.println("修改自己密码(1)");
System.out.println("重置密码(2)");
System.out.println("退出(else)");
int mm=scanner.nextInt();
        scanner.nextLine();
if(mm==1){
    back= password.changePassword(ID);
}else if(mm==2){
    back= password.resetPassword(ID);
}else{
   return true;
}
        return true;
    }

    private static boolean resetPassword(int id) {
       try{
           String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           String lowercase = "abcdefghijklmnopqrstuvwxyz";
           String digits = "0123456789";
           String specialCharacters = "!@#$%^&*()-_=+[]{}|;:,.<>?";
           String allCharacters = uppercase + lowercase + digits + specialCharacters;
           SecureRandom random = new SecureRandom();
           StringBuilder password = new StringBuilder();
           for (int i = 0; i <10; i++) {
               int randomIndex = random.nextInt(allCharacters.length());
               password.append(allCharacters.charAt(randomIndex));
           }
           if(id<500) {
               System.out.println("请输入要重置密码用户的ID：");
               String ID = scanner.nextLine();
               int wei;
               if(id!=1) {
                   wei = insider.indexOf(ID);
                   insider.set(wei + 2, "123456qweQWE@@@");
               }else{
                   wei = user.indexOf(ID);

                   user.set(wei + 2, password.toString());
               }
               System.out.println("修改成功");
               yingcheng.password.password1(id);
           }else{
         System.out.println("请输入重置密码的用户名");
         String username=scanner.nextLine();
         System.out.println("请输入要重置密码的用户邮箱地址");
         String address=scanner.nextLine();
         int wei=user.indexOf(username);
         user.set(wei+1,password.toString());
         System.out.println("随机生成的密码已经发到您的邮箱，您可以登录到邮箱查看登录密码");
        Login.login();
     }
     }catch (Exception e){
           System.out.println("找不到用户，请重新输入");
           password.resetPassword(id);
       }
        return true;
    }

    private static boolean changePassword(int a) {
        System.out.println("请输入你要修改后的密码");
        String changePassword1=scanner.nextLine();
        if(a<500)
        {
            int changeLocation=insider.indexOf(String.valueOf(a));
        insider.set(changeLocation+2,changePassword1);
        System.out.println("修改成功");
        password.password1(a);
        }else {
            String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(changePassword1);
            if(matcher.matches()){
                user.set(a+2,changePassword1);
                System.out.println("修改成功");
                password.password1(a);
            }
            else{
                System.out.println("修改失败，请重新输入");
                password.changePassword(a);
            }
        }
        return true;
    }


}
