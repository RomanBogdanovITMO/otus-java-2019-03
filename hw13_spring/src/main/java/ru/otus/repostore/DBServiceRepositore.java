package ru.otus.repostore;

import ru.otus.dataset.UserDataSet;

public interface DBServiceRepositore {
    void create(UserDataSet dataSet);
    UserDataSet load(long id);
}
