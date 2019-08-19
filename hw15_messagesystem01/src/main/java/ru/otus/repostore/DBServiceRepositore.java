package ru.otus.repostore;

import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Addressee;

import java.util.List;

public interface DBServiceRepositore extends Addressee {
    void create(UserDataSet dataSet);
    UserDataSet load(long id);
    List<UserDataSet> allUsers();
    public void init();
}
