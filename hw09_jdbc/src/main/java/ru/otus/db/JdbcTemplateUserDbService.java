package ru.otus.db;

import ru.otus.dao.User;

import java.sql.SQLException;
import java.util.logging.Logger;

public class JdbcTemplateUserDbService implements UserDBService {

    static Logger logger = Logger.getLogger(JdbcTemplateUserDbService.class.getName());

    public JdbcTemplateUserDbService() throws SQLException {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.createTables(H2DBConnection.getConnection());
    }

    @Override
    public void create(User user) throws SQLException, IllegalAccessException {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(H2DBConnection.getConnection());
        jdbcTemplate.create(user);
    }

    @Override
    public User load(User user) throws SQLException {
        logger.info("execute method load user: " + user.getId());

        final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>(H2DBConnection.getConnection());
        return jdbcTemplate.load(user.getId(),User.class);
    }
}
