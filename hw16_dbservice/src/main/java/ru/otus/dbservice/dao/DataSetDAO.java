package ru.otus.dbservice.dao;



import ru.otus.message_server.dataset.DataSet;

import java.util.List;

public interface DataSetDAO {
    <T extends DataSet> T save(T dataset) ;
    <T extends DataSet> T load(long id);
    <T extends DataSet> List<T> getAll();

}
