package yingcheng;
import java.util.Random;
import java.util.Scanner;

public class Manager extends password {
    static Scanner scanner = new Scanner(System.in);
    public static void manager() {
boolean back=false;
        System.out.println("欢迎，尊敬的管理员，您需要什么服务？");
        System.out.println("密码管理(1)");
        System.out.println("内部人员管理(2)");
        System.out.println("退出(else)");
        int service=scanner.nextInt();
        if(service==1){
            back= password.password1(3);
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
            int size1=insider.size()-3;
            System.out.println("用户ID、用户名、用户注册时间、用户类型、用户手机号、用户邮箱:");
            for(int y=0;y<size1;y++){
                if(y==2||y==9){
                    continue;
                }
               else{
                   System.out.print(insider.get(y)+" ");
                if(y==6){
                    System.out.println();
                }}
            }
            System.out.println();
            Manager.userManager();
        }else if(gl==2){
           do{ System.out.println("请输入要删除用户信息的id");
            String ID=scanner.nextLine();
            int location=insider.indexOf(ID);
            if(location==-1){
                System.out.println("找不到该用户，请重新输入");
                find=false;
            }else{
                System.out.println("是否确认删除(y/n)");
                String affirm=scanner.nextLine();
                if(affirm.equals("y")){
                    for (int i =0;i<7 ; i++) {
                         insider.remove(location);
                    }

                    System.out.println("删除完毕");
                }
                Manager.userManager();
            }
           }while(!find);
        }else if(gl==3) {
            System.out.println("您选择什么进行查询(1用户ID,2用户名)");
            int way = scanner.nextInt();
            if (way == 1) {
                do {
                    System.out.println("请输入要查询的用户id");
                    String ID = scanner.nextLine();
                    int location1 = insider.indexOf(ID);
                    if (location1 == -1) {
                        System.out.println("找不到该用户，请重新输入");
                        find = false;
                    } else {
                        for (int i = location1; i < location1 + 7; i++) {
                            System.out.print(insider.get(i));
                        }

                        System.out.println("查询完毕");
                        Manager.userManager();
                    }
                } while (!find);
            } else if (way == 2) {
                do {
                    System.out.println("请输入要查询的用户名");
                    String ID1 = scanner.nextLine();
                    int location2 = insider.indexOf(ID1);
                    if (location2 == -1) {
                        System.out.println("找不到该用户，请重新输入");
                        find = false;
                    } else {
                        for (int i = location2 - 1; i < location2 + 6; i++) {
                            System.out.print(insider.get(i));
                        }

                        System.out.println("查询完毕");
                        Manager.userManager();
                    }
                }while (!find) ;
            }
        } else if(gl==4){//用户ID、用户名、用户注册时间、用户类型（经理、前台）、用户手机号、用户邮箱；
add();
        }else if(gl==5){
System.out.println("请输入要修改用户的ID");
String id=scanner.nextLine();
int location=insider.indexOf(id);
        System.out.println("请输入要修改的部分，用户类型(1)、用户手机号(2)、用户邮箱(3)，用户名(4)");
        int part=scanner.nextInt();
            scanner.nextLine();
        System.out.println("请输入修改内容");
        String content=scanner.nextLine();
        if(part==1){
            insider.set(location+4,content);
        }else if(part==2){
            insider.set(location+5,content);
            }else if(part==3){
            insider.set(location+6,content);
        }else if(part==4){
            insider.set(location+1,content);
        }
        System.out.println("修改完成");
           Manager.userManager();
        }else{
            Manager.manager();
        }

}

    private static void add() {

        System.out.println("请输入增加用户Id");
        String content=scanner.nextLine();
        insider.add(content);
        if(inspect(content)){
            int length=insider.size();
            insider.remove(length-1);
            System.out.println("有重复，请重新输入");
            add();
        }
        System.out.println("请输入增加用户名");
        String content1=scanner.nextLine();
        insider.add(content1);
        if(inspect(content1)){
            int length=insider.size();
            insider.remove(length-1);
            System.out.println("有重复，请重新输入");
            add();
        }
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        insider.add(String.valueOf(random.nextInt(max - min + 1) + min));
        System.out.println("请输入增加用户注册时间");
        String content2=scanner.nextLine();
        insider.add(content2);
        System.out.println("请输入增加用户类型");
        String content3=scanner.nextLine();
        insider.add(content3);
        System.out.println("请输入增加用户手机号");
        String content4=scanner.nextLine();
        insider.add(content4);
        if(inspect(content4)){
            int length=insider.size();
            insider.remove(length-1);
            System.out.println("有重复，请重新输入");
            add();
        }
        System.out.println("请输入增加用户邮箱");
        String content5=scanner.nextLine();
        insider.add(content5);
        if(inspect(content5)){
            int length=insider.size();
            insider.remove(length-1);
            System.out.println("有重复，请重新输入");
            add();
        }
        System.out.println("增加成功");
        Manager.userManager();
    }

    private static boolean inspect(String content) {
        for (String s : insider) {
            if (s.equals(content)) {
                return true;

            }
            break;
        }
        return false;
    }

}
