package ru.otus.db;

import ru.otus.dao.MyId;
import ru.otus.executor.DBExcecutorImpl;
import ru.otus.helper.ReflectionHelper;
import ru.otus.helper.SqlHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate<T> {
    private final Connection connection;
    private static final String CREATE_TABLE_USER = "create table if not exists user" +
            "(id bigint(20) NOT NULL auto_increment, name varchar(255), age int)";
    private static final String CREATE_TABLE_ACCOUNT = "create table if not exists account" +
            "(id bigint(20) NOT NULL auto_increment, nameAccount varchar(255), valueAccount int)";



    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }


    public void createTables(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_USER)) {
            pst.executeUpdate();
        }
        System.out.println("createTable: sucsessful");
    }
    public void createTablesAccount(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_ACCOUNT)) {
            pst.executeUpdate();
        }
        System.out.println("createTable: sucsessful");
    }


    public void create(Object object) throws IllegalAccessException, SQLException {

        DBExcecutorImpl<Object> dbExcecutor = new DBExcecutorImpl<>(connection);
        Class<?> clazz = object.getClass();

        String sqlInsert = SqlHelper.getInsertSqlQuery(clazz);
        List<Object> paramObjects = ReflectionHelper.getParamObgect(object);

            long id = dbExcecutor.created(sqlInsert, paramObjects);
            System.out.println("id users: " + id);
            System.out.println("запрос: " + sqlInsert);
        }



    public T load(long id, Class<T> clazz) {

        String selectSqlQuery = SqlHelper.getSelectSqlQuery(clazz);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(selectSqlQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return ReflectionHelper.createInstance(resultSet, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*private List<Object> getParamObgect(Object object) throws IllegalAccessException {

        List<Object> paramObjects = new ArrayList<>();

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(MyId.class) == null) {
                paramObjects.add(field.get(object));
            }
        }
        return paramObjects;
    }*/
}
