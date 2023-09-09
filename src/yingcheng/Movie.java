package yingcheng;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie extends Main {
    static Scanner scanner=new Scanner(System.in);
    static String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt";
    static String filePath1 = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt";
     static boolean find() {
       try{ System.out.println("请选择单独查询(1)或组合查询(2)");
        int way=scanner.nextInt();
        scanner.nextLine();
        if(way==1) {
            System.out.println("请选择输入影片名称或导演或主演");
           String  content =scanner.nextLine();
            int location=check2(filePath,content);
            Manager.fileOperation3(location, filePath);
            return true;
        }else {
            System.out.println("你的组合:片名导演，导演主演，主演片名,");
            System.out.println("请输入内容");
            String content = scanner.nextLine();
            String content2 = scanner.nextLine();
            int location1 = check2(filePath, content);
            int location2 = check2(filePath, content2);
            if (location2 == location1) {
                Manager.fileOperation3(location1, filePath);

            } else {
                System.out.println("未查询到");

            }
        }

           return true;
    }catch (Exception e){
           System.out.println("未查询到");
           return true;
       }
    }

    static boolean delete() {
        System.out.println("请输入要删除的电影名");
        String name=scanner.nextLine();
        int wei=check2("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt",name);
       if(wei!=-1) {
           System.out.println("是否确认删除(y/n)");
           String select = scanner.nextLine();
           if (select.equals("y")) {
               Manager.fileOperation1(wei, filePath);
               System.out.println("删除成功");
           }
       }else{
                    System.out.println("没有该电影信息");
                }

        return true;
    }

    static boolean change() {
        try {
            System.out.println("请输入要修改电影名");
            String filmName = scanner.nextLine();
            int location =check2(filePath,filmName);
            System.out.println();
            System.out.println("请选择你要修改的内容：片名(1)、导演(2)、主演(3)、剧情简介(4)、时长(5)");
            int a = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入修改内容");
            String content = scanner.nextLine();
            List<String> fileContent = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int currentLineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (currentLineNumber == location) {
                        String[] words = line.split("\\s+");
                        words[a-1] = content;
                        line = String.join(" ", words);
                    }
                    fileContent.add(line);
                    currentLineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : fileContent) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("修改成功");

        }catch (Exception e){
            System.out.println("修改失败");
            change();
        }
        return true;
    }

    private static int check2(String filePath, String filmName) {
        return Login.checkLocation(filmName, filePath);
    }


    static boolean allList() {
        try {
            FileReader fileReader = new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine = bufferedReader.readLine();
            while (strLine != null){
                System.out.println(strLine);
                strLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException e){
            System.out.println("没有影片信息");
        }

        return true;
    }

    static boolean add() {
        try {
            fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt",true);
        } catch (IOException e) {
            e.printStackTrace();

        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
        System.out.println("请输入电影名");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入导演名");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入主演名");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入剧情简介");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("请输入时长");
            bufferedWriter.write(scanner.nextLine()+ " ");
        System.out.println("添加电影信息成功");
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    static boolean listSession() {
        try {

            FileReader fileReader = new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine = bufferedReader.readLine();
            while (strLine != null){
                System.out.println(strLine);
                strLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException e){
            System.out.println("没有影片信息");
        }
        return true;
    }

    static boolean deleteSession() {
        System.out.println("请输入你要删除的场次的电影名");
        String film = scanner.nextLine();
        System.out.println("请输入你要删除的场次的时间段");
        String time = scanner.nextLine();
        int location = check2(filePath1, film);
        int location1 = check2(filePath1, time);
        if (location1 == location) {
            System.out.println("是否确认删除(y/n)");
            String select = scanner.nextLine();
            if (select.equals("y")) {
                Manager.fileOperation1(location, filePath1);
                System.out.println("删除成功");
            } else {
                System.out.println("找不到该场次");
            }
        }
            return true;
        }

        static boolean changeSession () {
            System.out.println("请输入你要修改的场次的电影名和时间段");
            String film = scanner.nextLine();
            String time = scanner.nextLine();
            int location = check2(filePath1, film);
            int location1 = check2(filePath1, time);
            if (location1 == location) {
                System.out.println("请选择你要修改的内容：电影名(1)，放映厅(2)，时间段(3)，价格(4)");
                int select = scanner.nextInt();
                scanner.nextLine();
                System.out.println("请输入你要修改的内容");
                String content = scanner.nextLine();
                List<String> fileContent = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
                    String line;
                    int currentLineNumber = 1;
                    while ((line = br.readLine()) != null) {
                        if (currentLineNumber == location) {
                            String[] words = line.split("\\s+");
                            words[select - 1] = content;
                            line = String.join(" ", words);
                        }
                        fileContent.add(line);
                        currentLineNumber++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath1))) {
                    for (String line : fileContent) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("修改成功");
            } else {
                System.out.println("找不到该场次");
            }
            return true;
        }

        static boolean addSession () {
            try {
                fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt", true);
            } catch (IOException e) {
                e.printStackTrace();

            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            try {
                System.out.println("请输入电影名");
                bufferedWriter.write(scanner.nextLine() + " ");
                System.out.println("请输入放映厅");
                bufferedWriter.write(scanner.nextLine() + " ");
                System.out.println("请输入时间段");
                bufferedWriter.write(scanner.nextLine() + " ");
                System.out.println("请输入价格");
                bufferedWriter.write(scanner.nextLine() + " ");
                System.out.println("添加场次成功");
                bufferedWriter.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
