package app.server.models;

import app.server.entities.DocumentEntity;
import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;
import app.server.managers.database.DataManager;
import app.server.managers.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentModel<T extends DocumentEntity> extends AbstractModel<T> {

    public void send() throws SQLException {
        ResultSet res = DatabaseManager.execute("SELECT * FROM document");
        while (res.next()) {
            int documentNumber = res.getInt("document_id");
            String documentTitle = res.getString("document_title");

            DocumentEntity document = new DocumentEntity(documentNumber, documentTitle);

            DataManager.add(document);
        }
    }

    public void add(T document) throws SQLException {

        int documentNumber = document.getNumber();
        String documentTitle = document.getTitle();

        PreparedStatement res = DatabaseManager.prepare("INSERT INTO document VALUES (?, ?)");
        res.setInt(1, documentNumber);
        res.setString(2, documentTitle);

        res.executeUpdate();

        DataManager.add(document);
    }

    public void save(T document) throws SQLException {
        int documentNumber = document.getNumber();
        String documentTitle = document.getTitle();

        PreparedStatement res = DatabaseManager.prepare("UPDATE document SET document_title = ? WHERE document_id = ?");
        res.setString(1, documentTitle);
        res.setInt(2, documentNumber);

        res.executeUpdate();
    }

}
