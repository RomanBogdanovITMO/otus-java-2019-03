package otus.db;

import otus.dataset.UserDataSet;

public interface DBService {

    void create(UserDataSet dataSet);

    UserDataSet load(long id);
}
