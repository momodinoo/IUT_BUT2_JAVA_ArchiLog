package app.server.managers.database;

public class DatabaseConnector {
    public static void connect(String jdbc, String url) throws ClassNotFoundException {
        DatabaseManager.setJdbcClassName(jdbc);
        DatabaseManager.setUrl(url);
    }

}
