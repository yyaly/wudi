package yingcheng;
import java.util.Scanner;

public abstract class Reception extends Boss {
    static Scanner scanner =new Scanner(System.in);
    static Ticket bookingSystem = new Ticket();
    public static void reception() {
        boolean back=false;
       do{ System.out.println("欢迎，尊敬的前台人员，您需要什么服务？");
        System.out.println("列出全部影片信息(1)");
           System.out.println("列出全部场次信息(2)");
           System.out.println("列出指定电影场次信息(3)");
        System.out.println("售票(4)");
        System.out.println("退出(else)");
        int way=scanner.nextInt();
        if(way==1){
           back= Movie.allList();
        }else if(way==2){
            back= Movie.listSession();
        }else if(way==3){
            back= bookingSystem.run();
        }else if(way==4){
            back=bookingSystem.buy();
        }
        else{
            Main.main(null);
        }
       }while (back);
    }


}
