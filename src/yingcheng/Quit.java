package yingcheng;
import java.util.Scanner;
import java.security.*;
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


        public static String passwordChange(String password) throws Exception {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");  // 创建SHA-256对象
            sha256.update(password.getBytes());
            byte[] hashBytes = sha256.digest();  // 计算哈希值
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));  // 转换为十六进制字符串
            }

            return sb.toString();
        }

}

