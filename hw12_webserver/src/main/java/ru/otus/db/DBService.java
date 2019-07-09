package ru.otus.db;

import ru.otus.dataset.UserDataSet;

public interface DBService {
    String getLocalStatus();

    void create(UserDataSet dataSet);

    UserDataSet load(long id);

    void shutdown();
}
