package yingcheng;
import java.util.Scanner;
public class Quit {
    static Scanner scanner = new Scanner(System.in);

    static void quit() {
        System.out.println("是否确认退出（y/n）");
        String quit = scanner.nextLine();
        if (quit.equals("y")) {
            System.out.println("欢迎下次光临！");
            System.exit(0);
        }
    }

}

