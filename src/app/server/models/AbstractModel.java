package app.server.models;

import java.sql.SQLException;

public abstract class AbstractModel implements IModel{

    public AbstractModel() {

    }

    public abstract void send() throws SQLException;
}
