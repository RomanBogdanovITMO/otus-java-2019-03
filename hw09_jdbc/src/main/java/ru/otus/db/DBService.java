package ru.otus.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBService<T> {
    void createTables(Connection connection) throws SQLException;
    void create(Object object) throws IllegalAccessException, SQLException;
    T load(long id, Class<T> clazz) throws SQLException,InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException;

}
