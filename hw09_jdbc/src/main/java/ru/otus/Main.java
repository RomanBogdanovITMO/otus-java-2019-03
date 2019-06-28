package ru.otus;

import ru.otus.dao.User;
import ru.otus.db.H2DBConnection;
import ru.otus.db.JdbcTemplate;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException{

        User user = new User(0,"roman",3);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.createTables(H2DBConnection.getConnection());
        jdbcTemplate.create(user);
        Object user1 = jdbcTemplate.load(1,User.class);

        System.out.println(user1);



    }
}
