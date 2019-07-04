package ru.otus;

import ru.otus.dao.Account;
import ru.otus.dao.User;
import ru.otus.db.H2DBConnection;
import ru.otus.db.JdbcTemplate;
import ru.otus.db.JdbcTemplateUserDbService;
import ru.otus.db.UserDBService;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException{

        User user = new User(0,"roman",3);
        Account account = new Account("First",0,100);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.createTables(H2DBConnection.getConnection());
        jdbcTemplate.create(user);
         Object user1 = jdbcTemplate.load(1,User.class);
         System.out.println(user1);


        jdbcTemplate.createTablesAccount(H2DBConnection.getConnection());
        jdbcTemplate.create(account);
        Object account2 = jdbcTemplate.load(1,Account.class);
        System.out.println(account2);

        UserDBService service = new JdbcTemplateUserDbService();
        User user3 = new User(0,"lona",34);
        service.create(user3);
        Object user2 = service.load(user3);
        System.out.println(user2);
    }
}
