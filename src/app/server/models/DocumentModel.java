package app.server.models;

import app.server.entities.Document;
import app.server.entities.interfaces.IDocument;
import app.server.managers.database.DataManager;
import app.server.managers.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentModel extends AbstractModel {

    public void send() throws SQLException {
        ResultSet res = DatabaseManager.execute("SELECT * FROM document");
        while (res.next()) {
            int documentNumber = res.getInt("document_id");
            String documentTitle = res.getString("document_title");

            IDocument document = new Document(documentNumber, documentTitle);

            DataManager.add(document);
        }
    }

    public void add(Document document) throws SQLException {

        int documentNumber = document.getNumber();
        String documentTitle = document.getTitle();

        PreparedStatement res = DatabaseManager.prepare("INSERT INTO document VALUES (?, ?)");
        res.setInt(1, documentNumber);
        res.setString(2, documentTitle);

        res.executeQuery();

        DataManager.add(document);
    }

}
