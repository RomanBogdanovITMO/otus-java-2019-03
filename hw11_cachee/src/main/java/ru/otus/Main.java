package ru.otus;

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.CacheDBSerivceImpl;
import ru.otus.db.DBService;

public class Main {
    public static void main(String[] args) {

        DBService dbService = new CacheDBSerivceImpl();

        UserDataSet user1 = new UserDataSet("Ivan",24,new AddressDataSet("srteer01"),
                new PhoneDataSet("911"));

        dbService.create(user1);

        UserDataSet user2 = dbService.load(user1.getId());

        System.out.println(user2);
    }
}
