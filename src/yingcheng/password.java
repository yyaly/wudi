package yingcheng;

import java.io.*;
import java.security.SecureRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class password extends Main {
    static Scanner scanner=new Scanner(System.in );
    public static boolean password1(int ID) {

System.out.println("您需要什么服务？");
System.out.println("修改自己密码(1)");
System.out.println("重置密码(2)");
System.out.println("退出(else)");
int mm=scanner.nextInt();
        scanner.nextLine();
if(mm==1){
  password.changePassword(ID);
}else if(mm==2){
   password.resetPassword(ID);
}else{
   return true;
}
        return true;
    }

    private static boolean resetPassword(int id) {
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
               int lineNumber;
               String filePath;
               List<String> fileContent = new ArrayList<>();
               if(id==3) {
                   lineNumber = Integer.parseInt(ID);
                   filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt";

               }else{
                   lineNumber = 1;
                   filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt";
               }
               fileOperation4(lineNumber, filePath, fileContent);
               System.out.println("修改成功");
               yingcheng.password.password1(id);
           }else{
         System.out.println("请输入重置密码的用户名");
         String username=scanner.nextLine();
         System.out.println("请输入要重置密码的用户邮箱地址");
         String address=scanner.nextLine();
               int lineNumber=1;
               String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt";
               List<String> fileContent = new ArrayList<>();
               try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                   String line;
                   int currentLineNumber = 1;
                   while ((line = br.readLine()) != null) {
                       if (currentLineNumber == lineNumber) {
                           String[] words = line.split("\\s+");
                           try {
                               words[2] = Quit.passwordChange(password.toString());
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
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
         System.out.println("随机生成的密码已经发到您的邮箱，您可以登录到邮箱查看新的登录密码");
        Login.login();
     }
        return true;
    }

    private static void fileOperation4(int lineNumber, String filePath, List<String> fileContent) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] words = line.split("\\s+");
                    try {
                        words[2] = Quit.passwordChange("123456qweQWE@@@");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    line = String.join(" ", words);
                }
                fileContent.add(line);
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("修改失败");
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

    private static boolean changePassword(int a) {
        System.out.println("请输入你要修改后的密码");
    String changePassword1=scanner.nextLine();
        if(a<500)
        {
            String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt";
            List<String> fileContent = new ArrayList<>();

            fileOperation5(a, changePassword1, a, filePath, fileContent);
        }else {
            String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(changePassword1);
            if(matcher.matches()){
                int lineNumber=1;
                String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt";
                List<String> fileContent = new ArrayList<>();
                fileOperation5(a, changePassword1, lineNumber, filePath, fileContent);
            }
            else{
                System.out.println("修改失败，请重新输入");
                password.changePassword(a);
            }
        }
        return true;
    }

    private static void fileOperation5(int a, String changePassword1, int lineNumber, String filePath, List<String> fileContent) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] words = line.split("\\s+");
                    try {
                        words[2] = Quit.passwordChange(changePassword1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        System.out.println("修改成功");
        password.password1(a);
    }


}
