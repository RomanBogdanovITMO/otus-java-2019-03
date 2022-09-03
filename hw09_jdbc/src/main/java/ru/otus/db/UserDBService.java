package ru.otus.db;

import ru.otus.dao.User;

import java.sql.SQLException;


public interface UserDBService {

    void create(User user) throws SQLException, IllegalAccessException;
    User load(User user) throws SQLException;

}
