package app.server.models;

import app.server.entities.interfaces.IEntity;

import java.sql.SQLException;

public interface IModel<T extends IEntity> {

    void send() throws SQLException;

    void save(T entity) throws SQLException;

}
