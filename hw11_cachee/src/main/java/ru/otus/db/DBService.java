package ru.otus.db;

import ru.otus.dataset.UserDataSet;

public interface DBService {
    void create(UserDataSet dataSet);
    UserDataSet load(long id);
}
