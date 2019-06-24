package ru.otus.db;

import ru.otus.dao.MyId;
import ru.otus.executor.DBExcecutorImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate<T>implements DBService<T> {
    private final Connection connection;
    private static final String CREATE_TABLE_USER = "create table if not exists user" +
            "(id bigint(20) NOT NULL auto_increment, name varchar(255), age int)";


    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void createTables(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_USER)) {
            pst.executeUpdate();
        }
        System.out.println("createTable: sucsessful");
    }

    @Override
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
        }
        System.out.println(id);
        System.out.println(sqlInsert);
    }
    @Override
    public T load(long id, Class<T> clazz) throws SQLException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        String selectSqlQuery = getSelectSqlQuery(clazz);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(selectSqlQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return createInstance(resultSet, clazz);
            }
        }

    }
    private <T> String getSelectSqlQuery(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> listFieldName = new ArrayList<>();
        String idColumnName = "";

        for (Field field : fields) {
            if (field.getAnnotation(MyId.class) != null) {
                idColumnName = field.getName();
            }
            field.setAccessible(true);
            listFieldName.add(field.getName());
        }

        String columnNames = String.join(",", listFieldName);
        String tableName = clazz.getSimpleName();

        return String.format("SELECT %s FROM %s WHERE %s=?", columnNames, tableName, idColumnName);
    }

    private  <T> T createInstance(ResultSet resultSet, Class<T> clazz) throws SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        if (resultSet.next()) {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                field.set(instance, resultSet.getObject(field.getName()));
            }
            return instance;
        }
        return null;
    }

}
