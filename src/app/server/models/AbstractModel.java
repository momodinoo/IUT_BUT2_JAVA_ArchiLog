package app.server.models;

import app.server.entities.DocumentEntity;
import app.server.entities.interfaces.IEntity;

import java.sql.SQLException;

public abstract class AbstractModel<T extends IEntity> implements IModel<T>{

    public AbstractModel() {

    }

    public abstract void send() throws SQLException;

    public abstract void save(T entity) throws SQLException;
}
