package app.server.entities.interfaces;

import java.sql.SQLException;

public interface IEntity {

    void save() throws SQLException;

}
