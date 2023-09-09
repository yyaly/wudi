package yingcheng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Boss extends Main{
    static Scanner scanner=new Scanner(System.in);
    public static void boss() {
        boolean back=false;
        System.out.println("欢迎，尊敬的经理，您需要什么服务？");
        System.out.println("影片管理(1)");
        System.out.println("排片管理(2)");
        System.out.println("密码管理(3)");
        System.out.println("用户管理(4)");
        System.out.println("退出(else)");
       int  boss=scanner.nextInt();
        scanner.nextLine();
        if(boss==1){
            movie();
        }else if(boss==2)
    {
            arrange();
        }
        else if(boss==3)
        {
           back= password.password1(1);
           boss();
        }else if(boss==4)
        {
            customer();
        }
        else{
            Main.main(null);
        }}


        public static boolean arrange() {//影片，放映厅，时段，价格
            System.out.println("请选择你需要的服务");
            System.out.println("增加场次(1)");
            System.out.println("修改场次(2)");
            System.out.println("删除指定的片场信息(3)");
            System.out.println("列出所有场次信息(4)");
            System.out.println("退出(5)");
            int service=scanner.nextInt();
            scanner.nextLine();
            boolean hui=false;
            switch (service) {
                case 1 -> hui= Movie.addSession();
                case 2 ->hui= Movie.changeSession();
                case 3 -> hui= Movie.deleteSession();
                case 4 ->hui= Movie.listSession();
                case 5 -> Boss.boss();
            }
return hui;
        }


    private static void movie() {
        boolean back = false;
       do {
           System.out.println("请选择您需要的服务");//片名、导演、主演、剧情简介、时长
           System.out.println("列出所有影片信息(1)");
           System.out.println("添加影片信息(2)");
           System.out.println("修改影片信息(3)");
           System.out.println("删除影片信息(4)");
           System.out.println("查询影片信息(5)");
           System.out.println("退出(0)");
           int service = scanner.nextInt();
           scanner.nextLine();
           switch (service) {
               case 1 -> back = Movie.allList();
               case 2 -> back = Movie.add();
               case 3 -> back = Movie.change();
               case 4 -> back = Movie.delete();
               case 5 -> back = Movie.find();
               case 0 -> Boss.boss();
           }
       }while (back);
    }



    private static void customer() {
        System.out.println("请选择你的服务：列出所有用户信息(1),查询用户信息(2),退出(else)");
        int service=scanner.nextInt();
        if(service==1){
            try {

                FileReader fileReader = new FileReader("\"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt\"");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String strLine = bufferedReader.readLine();
                while (strLine != null){
                    System.out.println(strLine);
                    strLine = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }catch (IOException e){
                System.out.println("没有用户信息");
            }

            System.out.println();
             Boss.boss();
        }else if(service==2){
            System.out.println("您要以什么来查询，用户ID(1),用户名(2)");
            int way=scanner.nextInt();
            String id = null;
            switch (way) {
                case 1 -> {
                    System.out.println("请输入用户ID");
                    id = scanner.nextLine();

                }
                case 2 -> {
                    System.out.println("请输入用户名");
                  id = scanner.nextLine();

                }
            }
            int location1=Login.check1(id);
            try (BufferedReader br = new BufferedReader(new FileReader("\"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt\""))) {
                String line;
                int currentLineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (currentLineNumber == location1) {
                        System.out.println(line);
                        break;
                    }
                    currentLineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("查询完毕");
           Boss.boss();

        }else{
            Boss.boss();
        }
    }
}
