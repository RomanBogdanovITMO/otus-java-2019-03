package ru.otus.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExecutorConnection {

    private static final String URL = "jdbc:h2:/c:/otus-java-2019-03- 01/hw09_jdbc/h2/test";

    private static final String CREATE_TABLE_USER = "create table if not exists user " +
            "(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))";


    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }

    public void createTable(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_USER)) {
            pst.executeUpdate();
        }
        System.out.println("sucsessful");
    }
}
