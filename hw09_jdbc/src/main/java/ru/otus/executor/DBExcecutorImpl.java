package ru.otus.executor;

import java.sql.*;
import java.util.List;


public class DBExcecutorImpl<T> implements DBExecutor<T> {

    private final Connection connection;

    public DBExcecutorImpl(Connection connection) {
        this.connection = connection;
    }

    //добавляем user  в бд.
    @Override
    public long created(String sgl, List<Object> params) throws SQLException {
        long countUser = 0;
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sgl, Statement.RETURN_GENERATED_KEYS)) {
            for (int idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx));

            }
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                countUser = rs.getInt(1);

            }
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
        return countUser;
    }
}
