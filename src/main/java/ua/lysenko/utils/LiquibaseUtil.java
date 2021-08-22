package ua.lysenko.utils;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseUtil {
    private final String url = "jdbc:postgresql://ec2-63-33-14-215.eu-west-1.compute.amazonaws.com/d2n0g44s6qask4";
    private final String user = "zuwpoywbxvaqfm";
    private final String password = "9d776c78a0db40fc33d416beaf8bc9056174218965fcdcc9a172e5c9d1755acb";

    public void liquibaseStart() {
        java.sql.Connection c = null;
        Database database = null;
        try {
            c = DriverManager.getConnection(url, user, password);
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
        Liquibase liquibase = null;

        try {
            liquibase = new Liquibase("dbRes/dbchangelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            try {
                throw new DatabaseException(e);
            } catch (DatabaseException databaseException) {
                databaseException.printStackTrace();
            }
        } finally {
            if (c != null) {
                try {
                    c.rollback();
                    c.close();
                } catch (SQLException e) {
                    //nothing to do
                }
            }
        }
    }
}