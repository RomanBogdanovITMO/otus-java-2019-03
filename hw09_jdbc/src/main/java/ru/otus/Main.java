package ru.otus;

import ru.otus.dao.User;
import ru.otus.executor.DBExcecutorImpl;
import ru.otus.executor.ExecutorConnection;
import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        ExecutorConnection executorConection = new ExecutorConnection();
        Connection connection = executorConection.getConnection();
        executorConection.createTable(connection);

        User user = new User(0,"roman",3);
        DBExcecutorImpl<User> dbExcecutor = new DBExcecutorImpl<>(connection);
        dbExcecutor.getAnnotation(user);
        long userId = dbExcecutor.created("insert into user(name,age) values (?,?)", user);
        System.out.println(userId);

        Optional<User> user1 = dbExcecutor.load("select id, name from user where id  = ?", userId, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new User(resultSet.getLong("id"), resultSet.getString("name"),
                            resultSet.getInt("age"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        System.out.println(user1);
        connection.close();

    }
}
