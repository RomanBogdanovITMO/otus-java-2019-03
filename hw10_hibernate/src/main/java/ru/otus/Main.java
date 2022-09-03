package ru.otus;

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;


import java.util.Collections;
import java.util.logging.Logger;


public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        String statusHibernat = serviceHiber.getLocalStatus();
        logger.info(statusHibernat);

        PhoneDataSet phoneDataSet = PhoneDataSet.builder()
                .number("0000000000")
                .build();
        UserDataSet usr = UserDataSet.builder()
                .name("roman")
                .address(AddressDataSet.builder().street("5645frgrf").build())
                .age(40)
                .build();

        phoneDataSet.setUser(usr);
        usr.setPhoneDataSetList(Collections.singletonList(phoneDataSet));

        serviceHiber.create(usr);
        serviceHiber.create(usr);

        UserDataSet user = serviceHiber.load(usr.getId());
        logger.info(user.toString());

    }

}
