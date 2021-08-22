//package ua.lysenko.Service;
//
//import ua.lysenko.dao.equipmentActivityDao.SMSDao;
//import ua.lysenko.dao.equipmentDao.CellphoneDao;
//import ua.lysenko.entity.Subscriber;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.List;
//
//public class Service {
//    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    SMSDao smsDao = new SMSDao();
//    CellphoneDao cellphoneDao = new CellphoneDao();
//
//    public void run() {
//
//        System.out.println("Please choose operation: \n");
//        try {
//            choose();
//            String c = reader.readLine();
//            action(c);
//        } catch (IOException e) {
//            System.out.println("Sorry, something went wrong. Try again.");
//        }
//
//    }
//
//    private void action(String input) {
//        switch (Integer.parseInt(input)) {
//            case 1 -> findSMS();
//            case 2 -> getWeb();
//            case 3 -> getCall();
//            case 4 -> getSms();
//            case 5 -> getDevice();
//            case 0 -> System.exit(0);
//        }
//    }
//
//    private void choose() {
//        System.out.println();
//        System.out.println("1 - Return sender of sms");
//        System.out.println("2 - Show top 5 web active users");
//        System.out.println("3 - Show top 5 active calling users");
//        System.out.println("4 - Show top 5 active sms senders");
//        System.out.println("5 - Show most popular device used in the network");
//        System.out.println("0 - Exit");
//        System.out.println();
//    }
//
//    private void findSMS() {
//        System.out.println("\n" + "Please enter the word you wanna find in the storage: ");
//
//        try {
//            String input = reader.readLine();
//            List smsbyContent = smsDao.getSMSbyContent(input);
//            if (smsbyContent != null && !smsbyContent.isEmpty()) {
//                System.out.println(smsbyContent.get(0));
//            } else {
//                System.out.println("Not found in Database");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getWeb() {
//        cellphoneDao.getTopWebActiveCellphone().stream().forEach(System.out::println);
//    }
//
//    private void getSms() {
//        Subscriber[] objects = (Subscriber[]) cellphoneDao.getTopSMSsCellphone().stream().map(Subscriber::getName).toArray();
//        for (int i = 0; i < 5; i++) {
//            System.out.println(objects[i].getName());
//        }
//
//    }
//
//    private void getCall() {
//        System.out.println(cellphoneDao.getTopCallsCellphone().stream().map((Subscriber::getName)));
//    }
//
//    private void getDevice() {
//        System.out.println(cellphoneDao.getTopPopularCellphoneModel());
//    }
//}