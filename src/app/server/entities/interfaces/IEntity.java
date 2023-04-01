package app.server.entities.interfaces;

import java.sql.SQLException;

public interface IEntity {

    int getNumber();
    String getIdentifier();
    void save() throws SQLException;

}
