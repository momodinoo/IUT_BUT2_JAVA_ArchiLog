package app.server.entities;

import app.server.entities.interfaces.IDVD;
import app.server.entities.interfaces.IDocument;
import app.server.models.DVDModel;

import java.sql.SQLException;

public class DVDEntity implements IDVD {
    private final int     number;
    private final IDocument document;
    private final boolean isForAdults;

    public final static String PREFIX = "dvd-";

    public DVDEntity(int dvd_id, IDocument document, boolean isForAdults) {
        this.number = dvd_id;
        this.document = document;
        this.isForAdults = isForAdults;
    }

    @Override
        public String getIdentifier() {
            return PREFIX + this.getNumber();
        }

    public IDocument getDocument() {
        return this.document;
    }

    public boolean isForAdults() {
        return isForAdults;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void save() throws SQLException {
        DVDModel<DVDEntity> dvdModel = new DVDModel<>();

        dvdModel.save(this);
    }

    @Override
    public String toString() {
        return this.number + " - " + this.getDocument().getNumber() + " - " + isForAdults;
    }
}
