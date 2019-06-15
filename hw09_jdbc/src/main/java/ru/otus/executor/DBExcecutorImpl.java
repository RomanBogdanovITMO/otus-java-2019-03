package ru.otus.executor;

import ru.otus.dao.User;

import java.sql.Connection;

public class DBExcecutorImpl implements DBExecutor {

    private final Connection connection;

    public DBExcecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long saveUsers(User user) {
        return 0;
    }
}
