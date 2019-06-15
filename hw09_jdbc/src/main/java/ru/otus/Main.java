package ru.otus;


import ru.otus.executor.ExecutorConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        ExecutorConnection executorConection = new ExecutorConnection();
        Connection connection = executorConection.getConnection();
        executorConection.createTable(connection);
    }
}
