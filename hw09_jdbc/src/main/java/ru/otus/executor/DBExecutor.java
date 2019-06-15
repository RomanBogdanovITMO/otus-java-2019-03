package ru.otus.executor;

import ru.otus.dao.User;

public interface DBExecutor {
    long saveUsers(User user);
}
