package app.server.entities;

import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.ISubscriber;
import app.server.exceptions.RestrictionException;

public class Document implements IDocument {

    private final int number;
    private final String title;

    private ISubscriber borrower = null;
    private ISubscriber booker = null;


    public Document(int number, String title) {

        this.number = number;
        this.title = title;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
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

}
