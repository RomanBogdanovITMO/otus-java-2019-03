package ru.otus.repostore;

import org.springframework.stereotype.Repository;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;

@Repository
public class DBServiceRepositoreImpl implements DBServiceRepositore {

    @Override
    public void create(UserDataSet dataSet) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        serviceHiber.create(dataSet);
    }

    @Override
    public UserDataSet load(long id) {
        DBServiceHiber serviceHiber = new DBServiceHiber();

        return serviceHiber.load(id);
    }
}
