package yingcheng;

import java.util.Scanner;

public class Movie extends Main{
    static Scanner scanner=new Scanner(System.in);
    static boolean find() {
       try{ System.out.println("请选择单独查询(1)或组合查询(2)");
        int way=scanner.nextInt();
        scanner.nextLine();
        if(way==1) {
            System.out.println("请选择输入影片名称(1)或导演(2)或主演(3)");
            int way1=scanner.nextInt();
            scanner.nextLine();
            switch (way1) {
                case 1 -> {
                    System.out.println("请输入内容");
                    String content = scanner.nextLine();
                    int location = film.indexOf(content);
                    for (int i = location; i < location + 5; i++) {
                        System.out.print(film.get(i));
                    }
                    System.out.println("查询完毕");
                }
                case 2 -> {
                    System.out.println("请输入内容");
                    String content1 = scanner.nextLine();
                    int location1 = film.indexOf(content1);
                    for (int i = location1 - 1; i < location1 + 4; i++) {
                        System.out.print(film.get(i));
                    }   System.out.println("查询完毕");
                }
                case 3 -> {
                    System.out.println("请输入内容");
                    String content2 = scanner.nextLine();
                    int location2 = film.indexOf(content2);
                    for (int i = location2 - 2; i < location2 + 3; i++) {
                        System.out.print(film.get(i));
                    }   System.out.println("查询完毕");
                }

            }
            return true;
        }else{
            System.out.println("请选择你的组合:片名导演(1)，导演主演(2)，主演片名(3)");
            int way2=scanner.nextInt();
            scanner.nextLine();
            switch (way2) {
                case 1 -> {
                    System.out.println("请输入内容");
                    String content = scanner.nextLine();
                    String content2=scanner.nextLine();
                    int location1 = film.indexOf(content);
                    int location2=film.indexOf(content2);
                    if(location2-location1==1){
                        for (int i = location1; i < location1 + 5; i++) {
                            System.out.print(film.get(i));
                        }
                        }else{
                        System.out.println("未查询到");

                    }

                }
                case 2 -> {
                    System.out.println("请输入内容");
                    String content = scanner.nextLine();
                    String content2=scanner.nextLine();
                    int location1 = film.indexOf(content);
                    int location2=film.indexOf(content2);
                    if(location2-location1==1){
                        for (int i = location1-1; i < location1 + 4; i++) {
                            System.out.print(film.get(i));
                        }
                    }else{
                        System.out.println("未查询到");

                    }
                }
                case 3 -> {
                    System.out.println("请输入内容");
                    String content = scanner.nextLine();
                    String content2=scanner.nextLine();
                    int location1 = film.indexOf(content);
                    int location2=film.indexOf(content2);
                    if(location1-location2==2){
                        for (int i = location1-2; i < location1 + 3; i++) {
                            System.out.print(film.get(i));
                        }
                    }else{
                        System.out.println("未查询到");

                    }
                }
            }
        }
        System.out.println("查询完毕");
           return true;
    }catch (Exception e){
           System.out.println("未查询到");
           return true;
       }
    }

    static boolean delete() {
        System.out.println("请输入要删除的电影名");
        String name=scanner.nextLine();
        int wei=film.indexOf(name);
        System.out.println("是否确认删除(y/n)");
                String select=scanner.nextLine();
                if(select.equals("y")){
                    for(int i=0;i<5;i++){
                        film.remove(wei);
                    }
                    System.out.println("删除成功");
                    return true;
                }
        return true;
    }

    static boolean change() {
        try {
            System.out.println("请输入要修改电影名");
            String filmName = scanner.nextLine();
            int location = film.indexOf(filmName);
            for (int i = location; i < 5 + location; i++) {
                System.out.print(film.get(i));
            }
            System.out.println();
            System.out.println("请选择你要修改的内容：片名(1)、导演(2)、主演(3)、剧情简介(4)、时长(5)");
            int a = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入修改内容");
            String content = scanner.nextLine();
            film.set(location + a - 1, content);
            System.out.println("修改成功");

        }catch (Exception e){
            System.out.println("修改失败");
            change();
        }
        return true;
    }

    static boolean allList() {
        int length=film.size();
        if(length>4){int i=0;

            for (String s : film) {
                System.out.print(s + "    ");
               i++;
               if(i==5){
                   System.out.println();
                   i=0;
               }
            }

        }else{
            System.out.println("暂无影片信息");

        }
        return true;
    }

    static boolean add() {
        System.out.println("请输入电影名");
        film.add(scanner.nextLine());
        System.out.println("请输入导演名");
        film.add(scanner.nextLine());
        System.out.println("请输入主演名");
        film.add(scanner.nextLine());
        System.out.println("请输入剧情简介");
        film.add(scanner.nextLine());
        System.out.println("请输入时长");
        film.add(scanner.nextLine());
        System.out.println("添加电影信息成功");
        return true;
    }

    static boolean listSession() {
        int length=session.size();
        if(length>3){int i=0;

            for (String s : session) {
                System.out.print(s + "     ");
                i++;
                if(i==4){
                    System.out.println();
                    i=0;
                }
            }

        }else{
            System.out.println("暂无场次信息");

        }
        return true;
    }

    static boolean deleteSession() {
        System.out.println("请输入你要删除的场次的电影名");
        String film=scanner.nextLine();
        System.out.println("请输入你要删除的场次的时间段");
        String time=scanner.nextLine();
        int length=session.size();
        int wei;
        for(int i=0;i<length;i++){
            if(session.get(i).equals(film)&&session.get(i+2).equals(time)){
                wei=i;
                for(int y=0;y<4;y++){
                    session.remove(wei);
                }
                System.out.println("删除成功");
            }else{
                System.out.println("找不到该场次");
            }
        }


        return true;
    }

    static boolean changeSession() {
        System.out.println("请输入你要修改的场次的电影名和时间段");
        String film=scanner.nextLine();
        String time=scanner.nextLine();
        int length=session.size();
        int wei=0;
        for(int i=0;i<length;i++){
            if(session.get(i).equals(film)&&session.get(i+2).equals(time)){
                wei=i;
            }
        }
        if(wei!=0) {
            System.out.println("请选择你要修改的内容：电影名(1)，放映厅(2)，时间段(3)，价格(4)");
            int select=scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入你要修改的内容");
            String content=scanner.nextLine();
            switch (select) {
                case 1 -> session.set(wei, content);
                case 2 -> session.set(wei + 1, content);
                case 3 -> session.set(wei + 2, content);
                case 4 -> session.set(wei + 3, content);
            }
        }else{
            System.out.println("找不到该场次");
        }
        return true;
    }

    static boolean addSession() {
        System.out.println("请输入电影名");
        session.add(scanner.nextLine());
        System.out.println("请输入放映厅");
        session.add(scanner.nextLine());
        System.out.println("请输入时间段");
        session.add(scanner.nextLine());
        System.out.println("请输入价格");
        session.add(scanner.nextLine());
        System.out.println("添加场次成功");
        return true;
    }
}
