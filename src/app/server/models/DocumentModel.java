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

    @Override
    public void send() throws SQLException {
        ResultSet res = DatabaseManager.execute("SELECT * FROM document");
        while (res.next()) {
            int documentNumber = res.getInt("document_id");
            String documentTitle = res.getString("document_title");
            String documentState = res.getString("document_state");

            DocumentEntity document = new DocumentEntity(documentNumber, documentTitle, documentState);

            DataManager.add(document);
        }
    }

    @Override
    public void save(T document) throws SQLException {
        int documentNumber = document.getNumber();
        String documentState = document.getState();

        PreparedStatement  res = DatabaseManager.prepare("UPDATE document SET document_state = ? WHERE document_id = ?");
        res.setString(1, documentState);
        res.setInt(2, documentNumber);

        res.executeUpdate();
    }

}
