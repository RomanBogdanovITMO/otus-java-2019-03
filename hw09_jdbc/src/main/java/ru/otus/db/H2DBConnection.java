package ru.otus.db;

import java.sql.*;

public class H2DBConnection {

    private static final String URL = "jdbc:h2:/c:/otus-java-2019-03- 01/hw09_jdbc/h2/test";

    private H2DBConnection() {
    }

    public static Connection getConnection()throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }


}
