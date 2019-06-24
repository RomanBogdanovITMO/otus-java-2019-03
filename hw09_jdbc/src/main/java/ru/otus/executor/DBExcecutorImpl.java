package ru.otus.executor;
import ru.otus.dao.MyId;
import ru.otus.dao.User;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBExcecutorImpl<T> implements DBExecutor<T> {

    private final Connection connection;

    public DBExcecutorImpl(Connection connection) {
        this.connection = connection;
    }



    //добавляем user  в бд.
    @Override
    public long created (String sgl, List<Object> params) throws SQLException {
        long countUser = 0;
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            try (PreparedStatement pst = connection.prepareStatement(sgl, Statement.RETURN_GENERATED_KEYS)) {
                for (int idx = 0; idx < params.size(); idx++){
                    if (params.get(idx)instanceof String ){
                        pst.setString(idx+1,(String)params.get(idx));
                    }else if (params.get(idx)instanceof Integer){
                        pst.setInt(idx+1,(Integer)params.get(idx));
                    }
                }
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
          return countUser;
    }
    // читает user  из базы данных
    /*@Override
    public Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(rs));
            }
        }
    }*/


}
