package ru.otus.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface DBExecutor<T> {
    long created(String sgl, List<Object> params)throws SQLException;
   // <T> T load(long id,Class<T> clazz);

}
