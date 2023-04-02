package app.server.models;

import app.server.entities.DVDEntity;
import app.server.entities.DocumentEntity;
import app.server.entities.interfaces.IDocument;
import app.server.managers.database.DataManager;
import app.server.managers.database.DatabaseManager;
import app.server.utils.EntityUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDModel<T extends DVDEntity> extends AbstractModel<T> {

    @Override
    public void send() throws SQLException {
        ResultSet res = DatabaseManager.execute("SELECT * FROM dvd");
        while (res.next()) {
            int     dvdNumber      = res.getInt("dvd_id");
            int     documentNumber = res.getInt("document_id");
            boolean isForAdults    = res.getBoolean("dvd_adult");

            IDocument doc = (IDocument) DataManager.get(DocumentEntity.PREFIX + documentNumber);

            DVDEntity dvd = new DVDEntity(dvdNumber, doc, isForAdults);

            DataManager.add(dvd);
        }
    }

    @Override
    public void save(T dvd) throws SQLException {
        int     dvdNumber = dvd.getNumber();
        boolean forAdults = dvd.isForAdults();

        PreparedStatement res = DatabaseManager.prepare("UPDATE dvd SET dvd_adult = ? WHERE dvd_id = ?");
        res.setBoolean(1, forAdults);
        res.setInt(2, dvdNumber);

        res.executeUpdate();
    }

    public DVDEntity getDVDEntityByDocumentId(int documentId) {

        EntityUtils<DVDEntity> de = new EntityUtils<>(DVDEntity.class);

        for(DVDEntity dvd : de.getEntityList()) {
            if(dvd.getDocument().getNumber() == documentId) {
                return dvd;
            }
        }

        return null;
    }
}
