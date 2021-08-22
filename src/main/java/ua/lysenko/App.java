package ua.lysenko;

import ua.lysenko.utils.LiquibaseUtil;


public class App {


    public static void main(String[] args) {

        LiquibaseUtil liquibaseUtil = new LiquibaseUtil();
        liquibaseUtil.liquibaseStart();
//        Service service = new Service();
//        service.run();


    }
}
