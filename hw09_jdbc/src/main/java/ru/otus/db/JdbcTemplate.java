package ru.otus.db;

import ru.otus.dao.MyId;
import ru.otus.executor.DBExcecutorImpl;
import ru.otus.helper.ReflectionHelper;
import ru.otus.helper.SqlHelper;

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
        List<String> listFieldName = new ArrayList<>();
        List<String> listFieldNameSimvol = new ArrayList<>();
        List<Object> paramObjects = new ArrayList<>();
        String sqlInsert = null;

        DBExcecutorImpl<Object> dbExcecutor = new DBExcecutorImpl<>(connection);

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field fl : fields) {
            fl.setAccessible(true);
            paramObjects.add(fl.get(object));
            Annotation[] annotations = fl.getDeclaredAnnotations();
            for (Annotation an : annotations) {
                if (an instanceof MyId) {
                    for (Field fl1 : fields) {
                        if (!(fl1.getName().equals("Id"))) {

                            listFieldName.add(fl1.getName());
                        }
                    }
                    for (int i = 0; i < listFieldName.size(); i++){
                        String simvol = "?";
                        listFieldNameSimvol.add(simvol);
                    }
                    String tableName = clazz.getSimpleName();
                    String columnNames = String.join(",", listFieldName);
                    String valuesCount = String.join(",", listFieldNameSimvol);
                    sqlInsert = String.format("insert into %s (%s) values (%s)",tableName,columnNames,valuesCount);
                    System.out.println(sqlInsert);
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

        String selectSqlQuery = SqlHelper.getSelectSqlQuery(clazz);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(selectSqlQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return ReflectionHelper.createInstance(resultSet,clazz);
            }
        }

    }

}
