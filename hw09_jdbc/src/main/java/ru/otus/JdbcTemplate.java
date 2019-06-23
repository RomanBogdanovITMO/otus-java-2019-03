package ru.otus;

import ru.otus.dao.MyId;
import ru.otus.executor.DBExcecutorImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate {
    private final Connection connection;


    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public void create(Object object) throws IllegalAccessException, SQLException {
        String sqlParam1 = null;
        String sqlParam2 = null;
        String sqlInsert = null;
        List<Object> paramObjects = new ArrayList<>();

        DBExcecutorImpl<Object> dbExcecutor = new DBExcecutorImpl<>(connection);

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //System.out.println();
        for (Field fl : fields) {
            fl.setAccessible(true);
            if (fl.get(object) instanceof String) {
                String str = (String) fl.get(object);
                paramObjects.add(str);
            } else if (fl.get(object) instanceof Integer) {
                int parametr1 = (int) fl.get(object);
                paramObjects.add(parametr1);
            } else if (fl.get(object) instanceof Long) {
                long parametr2 = (long) fl.get(object);
                paramObjects.add(parametr2);
            }
            Annotation[] annotations = fl.getDeclaredAnnotations();
            // System.out.println(fl.getName());
            for (Annotation an : annotations) {
                if (an instanceof MyId) {
                    for (Field fl1 : fields) {
                        if (sqlParam1 == null) {
                            sqlParam1 = fl1.getName();
                        } else if (sqlParam2 == null) {
                            sqlParam2 = fl1.getName();
                        }
                    }
                    sqlInsert = "insert into " + clazz.getSimpleName().toLowerCase() +
                            "(" + sqlParam1.toLowerCase() + "," + sqlParam2.toLowerCase() + ") " + "values (?,?)";
                }
            }
        }
        System.out.println("before " + paramObjects);
        long id = dbExcecutor.created(sqlInsert, paramObjects);
        for (Field fl : fields) {
            fl.setAccessible(true);
            if (fl.get(object) instanceof Long) {
                System.out.println("bedore " + fl.get(object));
                fl.set(object, id);
                long parametr2 = (long) fl.get(object);
                System.out.println("after " + fl.get(object));
            }

       /* Method method = clazz.getMethod("getAge");
        if (method.invoke(object)instanceof String){

            System.out.println("yess");
        }*/


        }
        System.out.println(id);
        System.out.println(sqlInsert);
    }

    public void load(Object object,long id) throws NoSuchMethodException {
        String sqlParamId = null;
        String sqlParam2 = null;
        String sqlParam3 = null;
        String sqlSelect = null;

        DBExcecutorImpl<Object> dbExcecutor = new DBExcecutorImpl<>(connection);

        Class<?> clazz = object.getClass();
        Method method = clazz.getMethod("getAge");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields){
            if ((sqlParamId == null) & (field.getName().toLowerCase().equals("id"))) {
                sqlParamId = field.getName();
            } else if (sqlParam2 == null) {
                sqlParam2 = field.getName();
            }else if (sqlParam3 == null){
                sqlParam3 = field.getName();
            }
        }
        sqlSelect = "select " + sqlParamId.toLowerCase() + ", " + sqlParam2.toLowerCase() + ", " +
                sqlParam3.toLowerCase()+ " from " + clazz.getSimpleName().toLowerCase() +
                " where " + sqlParamId.toLowerCase() + " = ?";
        //как загрузить обьект из бд. если мы не знаем какой обьект будет на входе
       /* Optional<Object> obj = dbExcecutor.load(sqlSelect, id, (ResultSet resultSet) -> {
            try {
                if (resultSet.next()) {
                    return new Object(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });*/
        System.out.println(sqlSelect);

    }
}
