package ru.otus.db;

import ru.otus.dao.User;

import java.sql.SQLException;

public class JdbcTemplateUserDbService implements UserDBService {

    public JdbcTemplateUserDbService() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.createTables(H2DBConnection.getConnection());
    }

    @Override
    public User create(User user) throws SQLException, IllegalAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.create(user);
        return null;
    }

    @Override
    public User load(User user) throws SQLException {
        System.out.println(user.getId());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        return  (User) jdbcTemplate.load(user.getId(),User.class);
    }
}
