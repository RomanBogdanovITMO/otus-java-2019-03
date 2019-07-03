package ru.otus;

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;


public class Main {
    public static void main(String[] args) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        String statusHibernat = serviceHiber.getLocalStatus();
        System.out.println(statusHibernat);

        UserDataSet usr = new UserDataSet("pomdddd",40,new AddressDataSet("5645frgrf"),
                new PhoneDataSet("0000000000"));

        serviceHiber.create(usr);
       // PhoneDataSet phoneDataSet = new PhoneDataSet("03");
       // usr.addPhone(phoneDataSet);
        serviceHiber.create(usr);

        UserDataSet usre = serviceHiber.load(usr.getId());
        System.out.println(usre);

    }

}
