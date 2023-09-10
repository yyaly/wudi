package yingcheng;

import java.io.*;
import java.util.Scanner;

public class Main {
    static boolean time=false;
    interface ways{
        boolean buy();
        void showtime();
    }
    static FileWriter fileWriter;
    public static void main(String[] args) {
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\insider.txt.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("1" + " ");
            bufferedWriter.write("boss" + " ");
            bufferedWriter.write(Quit.passwordChange("123456") + " ");
            bufferedWriter.write("2023-08-26 15:30:45" + " ");
            bufferedWriter.write("经理" + " ");
            bufferedWriter.write("1234560" + " ");
            bufferedWriter.write("asdzxc" + " ");
            bufferedWriter.newLine();
            bufferedWriter.write("2" + " ");
            bufferedWriter.write("reception" + " ");
            bufferedWriter.write(Quit.passwordChange("456789") + " ");
            bufferedWriter.write("2023-08-26 15:31:21" + " ");
            bufferedWriter.write("前台" + " ");
            bufferedWriter.write("4567890" + " ");
            bufferedWriter.write("qweasd" + " ");
            bufferedWriter.newLine();
            bufferedWriter.write("3" + " ");
            bufferedWriter.write("admin" + " ");
            bufferedWriter.write(Quit.passwordChange("ynuinfo#777") + " ");

            bufferedWriter.flush(); // 刷新缓冲区，将数据写入文件
            time = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello,欢迎进入东南亚皇家影院！");
        System.out.println("登录(按1)");
        System.out.println("注册(按2)");
        System.out.println("退出(else)");
            int into=scanner.nextInt();
            if(into==1){
                Login.login();
            }else if(into==2){
                Register.register();
            }else{
                Quit.quit();
            }

    }

}
