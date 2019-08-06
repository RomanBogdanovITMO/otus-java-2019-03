package ru.otus.repostore;

import ru.otus.dataset.UserDataSet;

import java.util.List;

public interface DBServiceRepositore {
    void create(UserDataSet dataSet);
    List<UserDataSet> allUsers();
}
