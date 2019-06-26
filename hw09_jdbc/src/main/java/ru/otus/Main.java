package ru.otus;

import ru.otus.dao.User;
import ru.otus.db.DBService;
import ru.otus.db.H2DBConnection;
import ru.otus.db.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {

        DBService dbService = new JdbcTemplate(H2DBConnection.getConnection());
        dbService.createTables(H2DBConnection.getConnection());

        User user = new User(0,"roman",3);

        dbService.create(user);
        Object user1 = dbService.load(1,User.class);
        System.out.println(user1);//почему то возвращает null.



    }
}
