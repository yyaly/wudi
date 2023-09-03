package yingcheng;

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
    int accountLocation=user.indexOf(accountNumber);
    int passwordLocation=user.indexOf(password1);
    if (accountLocation!=-1&&passwordLocation-accountLocation==1) {
        System.out.println("用户登录成功！");
        User.user(Integer.parseInt(user.get(accountLocation-1)));
    }else if(accountNumber.equals(insider.get(15))&&password1.equals(insider.get(16))) {
        System.out.println("管理员登录成功！");
        Manager.manager();
    }else if(accountNumber.equals(insider.get(8))&&password1.equals(insider.get(9))){
        System.out.println("前台登录成功！");
        Reception.reception();
        
    }else if(accountNumber.equals(insider.get(1))&&password1.equals(insider.get(2))){
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


}
