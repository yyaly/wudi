package yingcheng;

import java.util.Scanner;

public abstract class User extends Main {
    static Scanner scanner = new Scanner(System.in);

public static boolean user(int ID){
    boolean continueRun=true;
    System.out.println("欢迎，尊敬的顾客"+ID+"，您需要什么服务？");
    System.out.println("密码管理(1)");
    System.out.println("购票(2)");
    System.out.println("退出(else)");
    int service=scanner.nextInt();
    scanner.nextLine();
    if(service==1){
        continueRun= password.password1(ID);
        user(ID);
    }else if(service==2){
          continueRun= buyTicket();
        user(ID);
}
    else{
        Main.main(null);
    }
    return false;
}

    private static boolean buyTicket() {
        boolean continueRun=false;
        Ticket bookingSystem = new Ticket();
    do {
        System.out.println("请选择你需要的服务");
        System.out.println("查看所有影片信息(1)");
        System.out.println("查看指定影片信息(2)");
        System.out.println("购票(3)");
        System.out.println("取票(4)");
        System.out.println("查看购票历史(5)");
        System.out.println("返回(0)");
        int service1 = scanner.nextInt();
        scanner.nextLine();
        switch (service1) {
            case 1 -> continueRun = Movie.allList();
            case 2 -> continueRun = bookingSystem.run();
            case 3 -> continueRun = bookingSystem.buy();
            case 4 -> continueRun = bookingSystem.takeTicket();
            case 5 -> continueRun = bookingSystem.buyTicketHistory();
            case 0 -> {
                return true;
            }
        }
    }while (continueRun);

        return continueRun;
    }}
