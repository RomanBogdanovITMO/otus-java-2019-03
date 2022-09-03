package ru.otus.executor;

import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class DBExecutorImpl<T> implements DBExecutor<T> {

    static Logger logger = Logger.getLogger(DBExecutorImpl.class.getName());
    private final Connection connection;

    //добавляем user  в бд.
    @Override
    public long created(final String sgl, final List<Object> params) throws SQLException {
        long countUser;
        final Savepoint savePoint = this.connection.setSavepoint("savePointName");
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
            logger.warning(ex.getMessage());
            throw ex;
        }
        return countUser;
    }
}
