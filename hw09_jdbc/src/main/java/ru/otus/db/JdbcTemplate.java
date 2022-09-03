package ru.otus.db;

import lombok.AllArgsConstructor;

import ru.otus.executor.DBExecutorImpl;
import ru.otus.helper.ReflectionHelper;
import ru.otus.helper.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


@AllArgsConstructor
public class JdbcTemplate<T> {
    static Logger logger = Logger.getLogger(H2DBConnection.class.getName());

    private final Connection connection;
    private static final String CREATE_TABLE_USER = "create table if not exists user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int)";
    private static final String CREATE_TABLE_ACCOUNT = "create table if not exists account(id bigint(20) NOT NULL auto_increment, nameAccount varchar(255), valueAccount int)";


    public void createTables(final Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_USER)) {
            pst.executeUpdate();
        }

        logger.info("createTable: successful");
    }

    public void createTablesAccount(final Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(CREATE_TABLE_ACCOUNT)) {
            pst.executeUpdate();
        }

        logger.info("createTable: successful");
    }


    public void create(final Object object) throws IllegalAccessException, SQLException {

        final DBExecutorImpl<Object> dbExecute = new DBExecutorImpl<>(connection);
        final Class<?> clazz = object.getClass();

        final String sqlInsert = SqlHelper.getInsertSqlQuery(clazz);
        final List<Object> paramObjects = ReflectionHelper.getParamObject(object);

        final long id = dbExecute.created(sqlInsert, paramObjects);
        logger.info("id users: " + id);
        logger.info("запрос: " + sqlInsert);
    }


    public T load(final long id, final Class<T> clazz) {

        final String selectSqlQuery = SqlHelper.getSelectSqlQuery(clazz);
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
}
