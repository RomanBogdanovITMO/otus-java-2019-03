package ru.otus;

import com.google.gson.Gson;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        final Person person = Person.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .age(38)
                .build();

        final JsonObjectWriter jW = new JsonObjectWriter();
        final Gson gson = new Gson();

        //создаем строку с помощью JsonObjectWriter
        final String strJson = jW.writeToJson(person);
        logger.info(strJson);

        // создаем новый обьект используя полученнуюу строку в формате json.
        final Person person1 = gson.fromJson(strJson, Person.class);
        logger.info(person1.getFirstName());

        if (strJson.equals(gson.toJson(person))) {

            logger.info("Object is serialised");
        }

    }
}
