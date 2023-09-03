package yingcheng;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static  ArrayList<String> insider=new ArrayList<>();
    public static ArrayList<String> user=new ArrayList<>();
    public static ArrayList<String> film=new ArrayList<>();
    public static ArrayList<String > session=new ArrayList<>();
    static boolean time=false;
    public static void main(String[] args) {
        if(!time) {
            insider.add("1");insider.add("boss");insider.add("123456");insider.add("2023-08-26 15:30:45");insider.add("经理");insider.add("123456");insider.add("asdzxc");
            insider.add("2");insider.add("reception");insider.add("456789");insider.add("2023-08-26 15:31:21");insider.add("前台");insider.add("456789");insider.add("qweasd");
            insider.add("3");insider.add("admin");insider.add("ynuinfo#777");
            time=true;
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
