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
            System.out.println(params);
            for (int idx = 0; idx < params.size(); idx++) {

                pst.setObject(idx + 1, params.get(idx));

            }
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                System.out.println(rs);
                countUser = rs.getInt(1);// здесь происходит ошибка
                // (Exception in thread "main" org.h2.jdbc.JdbcSQLException: Недопустимое значение "0" для параметра "columnIndex")
            }
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
        return countUser;
    }
}
