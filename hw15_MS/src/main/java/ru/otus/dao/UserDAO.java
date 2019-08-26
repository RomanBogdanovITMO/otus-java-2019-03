package ru.otus.dao;

import ru.otus.dataset.DataSet;

import java.util.List;

public interface UserDAO {
    <T extends DataSet> T save(T dataset);
    <T extends DataSet> T load(long id) ;
    <T extends DataSet> List<T> getAll();
}
