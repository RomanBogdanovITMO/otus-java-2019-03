package ru.otus;

import ru.otus.dao.MyId;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;

public class JdbcTemplate {
    private final Connection connection;


    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public void create(Object object)  {
        String param1 = null;
        String param2 = null;
        String sqlInsert = null;
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        System.out.println();
        for (Field fl : fields) {
            Annotation[] annotations = fl.getDeclaredAnnotations();
            System.out.println(fl.getName());
            for (Annotation an : annotations) {
                if (an instanceof MyId) {
                    for (Field fl1 : fields){
                        if (param1 == null){
                            param1 = fl1.getName();
                        }else if (param2 == null){
                            param2 = fl1.getName();
                        }
                    }
                     sqlInsert = "insert into " + clazz.getSimpleName().toLowerCase() +
                             "(" + param1.toLowerCase() + "," + param2.toLowerCase() + ") " + "values (?,?)";

                }
            }
        }
        System.out.println(sqlInsert);
    }
}
