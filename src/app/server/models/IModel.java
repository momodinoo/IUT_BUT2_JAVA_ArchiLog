package app.server.models;

import java.sql.SQLException;

public interface IModel {

    void send() throws SQLException;

}
