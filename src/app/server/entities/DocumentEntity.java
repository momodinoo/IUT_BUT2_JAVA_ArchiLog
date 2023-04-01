package app.server.entities;

import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.ISubscriber;
import app.server.exceptions.RestrictionException;
import app.server.models.DocumentModel;

import java.sql.SQLException;

public class DocumentEntity implements IDocument {

    private final int    number;
    private final String title;
    private       String state;

    private ISubscriber borrower = null;
    private ISubscriber booker   = null;

    public final static String PREFIX = "doc-";


    public DocumentEntity(int number, String title, String state) {
        this.number = number;
        this.title = title;
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String getIdentifier() {
        return PREFIX + this.getNumber();
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    @Override
    public ISubscriber getBorrower() {
        return borrower;
    }

    @Override
    public ISubscriber GetBooker() {
        return booker;
    }

    @Override
    public void setReservation(ISubscriber subscriber) throws RestrictionException {

    }

    @Override
    public void setBook(ISubscriber subscriber) throws RestrictionException {

    }

    @Override
    public void returnDocument() {

    }

    @Override
    public void save() throws SQLException {
        DocumentModel<DocumentEntity> documentModel = new DocumentModel<>();

        documentModel.save(this);
    }

    @Override
    public String toString() {
        String state = switch (this.state) {
            case "Available" -> "Libre";
            case "Booked" -> "Emprunté";
            case "Borrowed" -> "Réservé";
            default -> "Le Wakan Tanka l'a pris :(";
        };

        return this.number + " - " + this.title + " - " + state;
    }
}
