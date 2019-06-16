package ru.otus.executor;
import ru.otus.dao.MyId;
import ru.otus.dao.User;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Optional;
import java.util.function.Function;

public class DBExcecutorImpl<T> implements DBExecutor<T> {

    private final Connection connection;

    public DBExcecutorImpl(Connection connection) {
        this.connection = connection;
    }

// поверяем если в классе user  поля помеченные моей аннотацией
    public boolean getAnnotation(User user){
        boolean myAnnotation = false;
        Class<?> clazz = user.getClass();
        Field[]fields = clazz.getDeclaredFields();
        for (Field fl:fields){
            Annotation [] annotations = fl.getDeclaredAnnotations();
            for (Annotation an : annotations){
                if (an instanceof MyId){
                    myAnnotation = true;
                    //System.out.println(fl.getName());
                }
            }
        }
        return myAnnotation;
    }
    //добавляем user  в бд.
    @Override
    public long created (String sgl, User user ) throws SQLException {
        long countUser = 0;
        if(getAnnotation(user) == true) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            try (PreparedStatement pst = connection.prepareStatement(sgl, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, user.getName());
                pst.setInt(2, user.getAge());
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    rs.next();
                    countUser = rs.getInt(1);
                   // return rs.getInt(1);
                }
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
          return countUser;
    }
    // читает user  из базы данных
    @Override
    public Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(rs));
            }
        }
    }
}
