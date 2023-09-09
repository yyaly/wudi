package yingcheng;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Ticket extends Main implements Main.ways {
    private final Random random = new Random();
    static Scanner scanner=new Scanner(System.in);
int[][] seats;

    public boolean buyTicketHistory(int id) {
        String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt";
        String targetString = String.valueOf(id);
        String nextString;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+"); // 使用空格分隔字符串
                for (int i = 0; i < words.length - 1; i++) {
                    if (words[i].equals(targetString)) {
                        nextString = words[i + 1];
        int location=Login.checkLocation(nextString,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt");
        try (BufferedReader br1 = new BufferedReader(new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt"))) {
            String line1;
            int currentLine = 1;
            while ((line1 = br1.readLine()) != null) {
                if (currentLine == location+1) {
                    System.out.println(line1);
                    break;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("查询完毕");
        return true;
    }

    public boolean check(String movieName,String showtime) {
        showtime();
        int location=Login.checkLocation(movieName,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt");
        int location1=Login.checkLocation(showtime,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt" );
       if(location==-1||location1==-1){
           System.out.println("没有该场次");
           return false;
       }else{
           System.out.println("座位信息：");
           String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt";
           int totalLines = 10; // 要读取的总行数
           try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
               String line;
               int currentLine = 2;
               while ((line = br.readLine()) != null) {
                   if (currentLine >= location && currentLine < location + totalLines) {
                       System.out.println(line);
                   }
                   if (currentLine == location + totalLines) {
                       break; // 如果达到总行数，跳出循环
                   }
                   currentLine++;
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        return true;
    }

    public boolean buy() {//放映厅、放映时间、片长、片名、座位信息
       showtime();
        try {
            FileWriter fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        int location = Login.check1(username);
        if(location==-1){
            System.out.println("用户名输入错误，请重新输入");
            buy();
        }
            bufferedWriter.write(username+" ");
            bufferedWriter.newLine();
        System.out.print("请输入电影名：");
        String movieName = scanner.nextLine();
        System.out.print("请输入场次：");
        String showtime = scanner.nextLine();
        boolean inspection =check(movieName,showtime);
        int otherLocation=Login.checkLocation(movieName,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt");
        if (inspection) {
             System.out.println("您要购买几张票：");
             int ticketAmount = 0;
            boolean validInput = false;
            while (!validInput) {//检查输入
                try {
                    ticketAmount = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("输入非法，请输入整数。");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
             seats = new int[ticketAmount][2];
             for (int i = 0; i < ticketAmount; i++) {
                 boolean success;
                 do {
                     try {//检查输入
                         System.out.print("请输入要预定座位的行号：");
                         int row = scanner.nextInt();
                         System.out.print("请输入要预定座位的列号：");
                         int seatNumber = scanner.nextInt();
                         scanner.nextLine();
                         success = change(row+otherLocation+1,seatNumber+1,"X");
                         if (success) {
                             System.out.println("成功预定座位：" + row + " 排 " + seatNumber + " 号");
                             seats[i][0] = row;
                             seats[i][1] = seatNumber;
                         } else {
                             System.out.println("预定座位失败，该座位已被预订或没有该座位。");
                         }
                     } catch (InputMismatchException e) {
                         System.out.println("输入非法，请输入整数。");
                         scanner.nextLine();
                         success = false;
                     }
                 } while (!success);
             }
             int sessionLocation=Login.checkLocation(showtime,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt");
            int movieLocation =Login.checkLocation(movieName,"D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt");
            double price;
            double userLevel = Double.parseDouble(getStringInFile( "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt",location,9));
            if (userLevel < 200) {
                System.out.println("您为铜牌用户");
                price = Double.parseDouble(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt",sessionLocation,4)) * ticketAmount;
            } else if (userLevel >=200 && userLevel < 500) {
                System.out.println("您为银牌用户");
                price = 0.95 * Double.parseDouble(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt",sessionLocation,4)) * ticketAmount;
            } else {
                System.out.println("您为金牌用户");
                price = 0.88 * Double.parseDouble(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt",sessionLocation,4)) * ticketAmount;
            }
            System.out.println("您需要支付金额：" + price);
            System.out.println("请在2分钟内使用支付宝、微信、银行卡支付：");
           String payMoney=scanner.nextLine();
            System.out.println("支付成功");
            int times= Integer.parseInt(getStringInFile( "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt",location,10));
              change1(location,9, String.valueOf(price+userLevel));
            change1(location,10, String.valueOf(times+1));
                int ticketIDPart1 = random.nextInt(1000);
                int ticketIDPart2 = random.nextInt(1000);
                String hand = "XAD";
                String end = "DG";
                String ticketID = hand + "-" + ticketIDPart1 + "-" + ticketIDPart2 + end;
                System.out.println(ticketID);
            bufferedWriter.write(ticketID+" ");
                System.out.println(movieName);
            bufferedWriter.write(movieName+" ");
                System.out.println(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt",sessionLocation,2));
            bufferedWriter.write(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt",sessionLocation,2)+" ");
                System.out.println(showtime);
            bufferedWriter.write(showtime+" ");
                System.out.println(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt",movieLocation,5));
            bufferedWriter.write(getStringInFile("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\film.txt.txt",movieLocation,5)+" ");
                System.out.print("座位信息：");
                for (int i = 0; i < ticketAmount; i++) {
                    System.out.print(seats[i][0] + "排" + seats[i][1] + "号 ");
                    bufferedWriter.write(seats[i][0] + "排" + seats[i][1] + "号 ");
                }
                System.out.println();
            bufferedWriter.write(formattedDateTime);

        } else {
            System.out.println("没有该场次。");
            return true;
        }
            bufferedWriter.flush();
            fileWriter.close();
            bufferedWriter.close();
        }catch (IOException e){
            return true;
        }
        return true;
    }

    private void change1(int location, int i, String valueOf) {
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt"))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == location) {
                    String[] words = line.split("\\s+");
                    if (i <= words.length) {
                        words[i - 1] =valueOf;
                    }
                    line = String.join(" ", words);
                }
                fileContent.add(line);
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\user.txt.txt"))) {
            for (String line : fileContent) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean change(int row, int seatNumber, String x) {//改变某行某个位子
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt"))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == row) {
                    String[] words = line.split("\\s+");
                    if (seatNumber <= words.length) {
                        if(words[seatNumber - 1].equals(x)){
                            return false;
                        }
                        words[seatNumber - 1] =x;
                    } else {
                        return false;
                    }

                    line = String.join(" ", words);
                }
                fileContent.add(line);
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt"))) {
            for (String line : fileContent) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void showtime() {
        try {
            String firstString;
            String thirdString;
            String filePath = "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\session.txt.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length >= 3) {
                    firstString = words[0];
                    thirdString = words[2];
                    FileWriter fileWriter = new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.newLine();
                    int location = Login.checkLocation(firstString, "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt");
                    int location1 = Login.checkLocation(thirdString, "D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\showTime.txt.txt");
                    if (location == -1 || location1 == -1) {
                        bufferedWriter.write(Objects.requireNonNull(firstString) + "  ");
                        bufferedWriter.write(thirdString);
                        bufferedWriter.newLine();
                        bufferedWriter.write("  1 2 3 4 5 6 7 8 9 10 11 12");
                        bufferedWriter.newLine();
                        for (int i = 1; i < 8; i++) {
                            bufferedWriter.write(i + " ");
                            for (int y = 1; y < 13; y++) {
                                bufferedWriter.write("O ");
                            }
                            bufferedWriter.newLine();
                        }
                    }

                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                br.close();
            }}
            } catch(IOException e){
            System.out.println();
            }
        }

    public boolean takeTicket() {
        System.out.println("请输入电影票的电子ID编号：");
        String number = scanner.nextLine();
        boolean take=false;
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(number)) {
                    take=true;
                    System.out.println(line); // 输出包含指定字符串的行内容
                }
            }
            if(!take){
                System.out.println("票已被取，不能重复取票");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(number)) {
                    line = line.replace(number, "");
                    if (line.trim().isEmpty()) {
                        continue; // 如果删除后行为空，则跳过该行
                    }
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\IntelliJ IDEA 2021.1.3\\yingcheng\\history.txt.txt"))) {
            for (String line : fileContent) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    private static String getStringInFile(String filePath, int lineNumber, int position) {
        String result = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] strings = line.split(" ");
                    if (strings.length >= position) {
                        result = strings[position - 1];
                    }
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
