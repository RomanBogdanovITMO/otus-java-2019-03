package ru.otus.db;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
public class H2DBConnection {

    private static final String URL = "jdbc:h2:/c:/otus-java-2019-03- 01/hw09_jdbc/h2/test";
    static Logger logger = Logger.getLogger(H2DBConnection.class.getName());

    public static Connection getConnection() throws SQLException {
        logger.info("execute method getConnection: ");

        final Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }


}
