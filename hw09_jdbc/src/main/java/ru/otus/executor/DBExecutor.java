package ru.otus.executor;

import ru.otus.dao.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public interface DBExecutor<T> {
    long created(String sgl, User user)throws SQLException;
    Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException;

}
