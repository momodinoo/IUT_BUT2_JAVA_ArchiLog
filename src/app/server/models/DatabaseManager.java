package app.server.models;

import java.sql.*;

public class DatabaseManager {

    private static String jdbcClassName;
    private static String url;

    private static Connection _databaseInstance = null;

    public static void setJdbcClassName(String jdbcClassName) throws ClassNotFoundException {
        DatabaseManager.jdbcClassName = jdbcClassName;

        Class.forName(DatabaseManager.jdbcClassName);
    }

    public static void setUrl(String url) {
        DatabaseManager.url = url;
    }

    public static Connection getInstance() throws SQLException {
        if (DatabaseManager._databaseInstance == null) {
            DatabaseManager._databaseInstance = DriverManager.getConnection(url);
        }

        return DatabaseManager._databaseInstance;
    }

    public static ResultSet execute(String stmt) throws SQLException {
        Statement statement = DatabaseManager.getInstance().createStatement();

        return statement.executeQuery(stmt);
    }

    public static void close() throws SQLException {
        DatabaseManager.getInstance().close();
    }


    /**
     * TODO THIS LIST:
     * Database driver
     * Database Verification (tables ?)
     */
}