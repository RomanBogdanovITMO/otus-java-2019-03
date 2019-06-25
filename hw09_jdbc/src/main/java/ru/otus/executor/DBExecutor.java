package ru.otus.executor;

import java.sql.SQLException;
import java.util.List;


public interface DBExecutor<T> {
    long created(String sgl, List<Object> params)throws SQLException;
   // <T> T load(long id,Class<T> clazz);

}
