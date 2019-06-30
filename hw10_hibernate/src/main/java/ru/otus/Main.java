package ru.otus;

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.JdbcTemplateHibernate;


public class Main {
    public static void main(String[] args) {
        JdbcTemplateHibernate hibernate = new JdbcTemplateHibernate();
        String st = hibernate.getLocalStatus();
        System.out.println("STATUS->>>> " + st);
        hibernate.create(new UserDataSet("roman", 20, new AddressDataSet("st.roman"), new PhoneDataSet("911")));
        UserDataSet user1 = hibernate.load(1);


    }
}
