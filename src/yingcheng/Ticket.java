package yingcheng;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Ticket extends Main {
    private final ArrayList<ArrayList<String>> ticketHistory = new ArrayList<>();
    private final Random random = new Random();
    private final Scanner scanner;
    private final MovieTheater movieTheater;
    int [][]seats;
    private static class Seat {
        private boolean booked;

        public Seat() {
            this.booked = false;
        }

        public boolean isBooked() {
            return booked;
        }

        public void book() {
            booked = true;
        }
        public void book1() {
            booked = false;
        }
    }

    private static class MovieSession {
        private final String showtime;
        private final List<Seat> seats;

        public MovieSession(String showtime, int totalSeats) {
            this.showtime = showtime;
            this.seats = new ArrayList<>();
            for (int i = 0; i < totalSeats; i++) {
                seats.add(new Seat());
            }
        }

        public boolean bookSeat(int row, int seatNumber) {
            if (row < 1 || row > 7 || seatNumber < 1 || seatNumber > 12) {
                return false;
            }

            int index = (row - 1) * 12 + seatNumber - 1;
            Seat seat = seats.get(index);
            if (!seat.isBooked()) {
                seat.book();
                return true;
            }
            return false;
        }
        public void bookSeat1(int row1, int seatNumber1) {
            int index = (row1 - 1) * 12 + seatNumber1 - 1;
            Seat seat1 = seats.get(index);
                seat1.book1();
        }

        public String getShowtime() {
            return showtime;
        }

        public String getSeatInfo() {
            StringBuilder seatInfo = new StringBuilder();
            seatInfo.append("座位信息： ").append(showtime).append(":\n");
            seatInfo.append("   ");
            for (int i = 1; i <= 12; i++) {
                seatInfo.append(String.format("%-4d", i));
            }
            seatInfo.append("\n");

            for (int row = 1; row <= 7; row++) {
                seatInfo.append(String.format("%-3d", row));
                for (int col = 1; col <= 12; col++) {
                    Seat seat = seats.get((row - 1) * 12 + col - 1);
                    seatInfo.append(seat.isBooked() ? "  X " : "  O ");
                }
                seatInfo.append("\n");
            }
            return seatInfo.toString();
        }
    }

    private static class MovieTheater {
        private final Map<String, MovieSession> showTimes;
        public MovieTheater() {
            this.showTimes = new HashMap<>();
            int length = session.size();
            for (int i = 0; i < length; i++) {
                if (i > 0 && (i - 2) % 4 == 0) {
                    addShowtime(session.get(i));
                }
            }
        }
        public void addShowtime(String showtime) {
            int totalSeats = 7 * 12; // 七排每排12个座位
            showTimes.put(showtime, new MovieSession(showtime, totalSeats));
        }

        public MovieSession getShowtime(String showtime) {
            return showTimes.get(showtime);
        }
    }
    public Ticket() {
        scanner = new Scanner(System.in);
        movieTheater = new MovieTheater();
    }
    public boolean buyTicketHistory() {
        if (ticketHistory.isEmpty()) {
            System.out.println("暂无购票历史记录");
        } else {
            System.out.println("购票历史记录：");
            for (ArrayList<String> ticketInfo : ticketHistory) {
                for (String info : ticketInfo) {
                    System.out.print(info + "  ");
                }
                System.out.println();
            }
        }
        return true;
    }

    public boolean run() {
        System.out.print("请输入电影名：");
        String movieName = scanner.nextLine();
        System.out.print("请输入场次：");
        String showtime = scanner.nextLine();
        MovieSession selectedShowtime = movieTheater.getShowtime(showtime);
        if (selectedShowtime != null) {
            String seatInfo = selectedShowtime.getSeatInfo();
            System.out.println(movieName + " - " + selectedShowtime.getShowtime() + " 座位信息：");
            System.out.println(seatInfo);
        } else {
            System.out.println("无效的场次。");
        }
        return true;
    }

    public boolean buy() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入电影名：");
        String movieName = scanner.nextLine();
        System.out.print("请输入场次：");
        String showtime = scanner.nextLine();
        MovieSession selectedShowtime = movieTheater.getShowtime(showtime);
        if (selectedShowtime != null) {
            String seatInfo = selectedShowtime.getSeatInfo();
            System.out.println(movieName + " - " + selectedShowtime.getShowtime() + " 座位信息：");
            System.out.println(seatInfo);
        System.out.println("您要购买几张票：");
            int ticketAmount = scanner.nextInt();
            seats = new int[ticketAmount][2];
            for (int i = 0; i < ticketAmount; i++) {
                boolean success;
               do {
                   System.out.print("请输入要预定座位的行号：");
                   int row = scanner.nextInt();
                   System.out.print("请输入要预定座位的列号：");
                   int seatNumber = scanner.nextInt();
                   scanner.nextLine();
                   success = selectedShowtime.bookSeat(row, seatNumber);
                   if (success) {
                       System.out.println("成功预定座位：" + row + " 排 " + seatNumber + " 号");
                       seats[i][0] = row;
                       seats[i][1] = seatNumber;
                   } else {
                       System.out.println("预定座位失败，该座位已被预订或没有该座位。");
                   }
               }while (!success);
            }

            int location = user.indexOf(username);
            int location1 = film.indexOf(movieName);
            int movieLocation = session.indexOf(movieName);
            double price;
            double userLevel = Double.parseDouble(user.get(location + 6));
            if (userLevel < 200) {
                System.out.println("您为铜牌用户");
                price = Double.parseDouble(session.get(movieLocation + 3)) * ticketAmount;
            } else if (userLevel >=200 && userLevel < 500) {
                System.out.println("您为银牌用户");
                price = 0.95 * Double.parseDouble(session.get(movieLocation + 3)) * ticketAmount;
            } else {
                System.out.println("您为金牌用户");
                price = 0.88 * Double.parseDouble(session.get(movieLocation + 3)) * ticketAmount;
            }
            System.out.println("您需要支付金额：" + price);
            System.out.println("请在2分钟内使用支付宝、微信、银行卡支付：");
           String payMoney=scanner.nextLine();
            System.out.println("支付成功");
                user.set(location + 6,String.valueOf( user.get(location + 6) + price));
                user.set(location + 7, String.valueOf(user.get(location + 6) + 1));
                int ticketIDPart1 = random.nextInt(1000);
                int ticketIDPart2 = random.nextInt(1000);
                String hand = "XAD";
                String end = "DG";
                String ticketID = hand + "-" + ticketIDPart1 + "-" + ticketIDPart2 + end;
                ArrayList<String> ticketInfo = new ArrayList<>();
                System.out.println(ticketID);
                ticketInfo.add(ticketID);
                System.out.println(movieName);
                ticketInfo.add(movieName);
                System.out.println(session.get(movieLocation + 1));
                ticketInfo.add(session.get(movieLocation + 1));
                System.out.println(showtime);
                ticketInfo.add(showtime);
                System.out.println(film.get(location1 + 4));
                ticketInfo.add(film.get(location1 + 4));
                System.out.print("座位信息：");
                for (int i = 0; i < ticketAmount; i++) {
                    System.out.print(seats[i][0] + "排" + seats[i][1] + "号 ");
                    ticketInfo.add(seats[i][0] + "排" + seats[i][1] + "号");
                }
                System.out.println();
                ticketInfo.add(formattedDateTime);
                ticketHistory.add(ticketInfo);


        } else {
            System.out.println("没有该场次。");
            return true;
        }
        return true;
    }

    public boolean takeTicket() {
        System.out.println("请输入电影票的电子ID编号：");
        String number = scanner.nextLine();
        if (!ticketHistory.isEmpty() && number.equals(ticketHistory.get(0).get(0))) {
            ArrayList<String> ticketInfo = ticketHistory.get(0);
            for (String info : ticketInfo) {
                System.out.print(info + "  ");
            }
            System.out.println("取票成功");
        } else {
            System.out.println("请勿重复取票或输入无效的电子ID编号");
        }
        return true;
    }


}
