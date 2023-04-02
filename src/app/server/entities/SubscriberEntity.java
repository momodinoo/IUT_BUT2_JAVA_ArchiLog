package app.server.entities;

import app.server.entities.interfaces.ISubscriber;
import app.server.models.SubscriberModel;

import java.sql.SQLException;
import java.util.Date;

public class SubscriberEntity implements ISubscriber {
    private final int number;
    private final String name;
    private final Date dateOfBirth;

    public final static String PREFIX = "sub-";

    public SubscriberEntity(int number, String name, Date DoB) {
        this.number = number;
        this.name = name;
        this.dateOfBirth = DoB;
    }

    @Override
    public String getIdentifier() {
        return PREFIX + this.getNumber();
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public void save() throws SQLException {
        SubscriberModel <SubscriberEntity> subscriberModel = new SubscriberModel<>();

        subscriberModel.save(this);
    }

    @Override
    public String toString() {
        return this.number + " - " + this.name + " - " + this.dateOfBirth;
    }
}
