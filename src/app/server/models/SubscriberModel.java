package app.server.models;

import app.server.entities.DVDEntity;
import app.server.entities.DocumentEntity;
import app.server.entities.SubscriberEntity;
import app.server.entities.interfaces.IDocument;
import app.server.managers.database.DataManager;
import app.server.managers.database.DatabaseManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriberModel<T extends SubscriberEntity> extends AbstractModel<T> {
    @Override
    public void send() throws SQLException {
        ResultSet res = DatabaseManager.execute("SELECT * FROM subscriber");
        while (res.next()) {
            int subscriberID = res.getInt("subscriber_id");
            String subscriberName = res.getString("subscriber_name");
            Date subscriberBirthDate = res.getDate("subscriber_birthdate");

            SubscriberEntity sub = new SubscriberEntity(subscriberID, subscriberName, subscriberBirthDate);

            DataManager.add(sub);
        }
    }

    @Override
    public void save(T entity) throws SQLException {
        //TODO empty method ??
    }

}
