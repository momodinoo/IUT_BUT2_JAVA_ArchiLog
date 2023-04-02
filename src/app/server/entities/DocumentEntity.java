package app.server.entities;

import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.ISubscriber;
import app.server.exceptions.RestrictionException;
import app.server.managers.database.DataManager;
import app.server.models.DocumentModel;
import app.server.utils.EntityUtils;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DocumentEntity implements IDocument {

    private final int     number;
    private final String  title;
    private       String  state;
    private       Integer idSubscriber;

    private ISubscriber borrower = null;
    private ISubscriber booker   = null;

    private       Timer timerBooking;
    private final int   BOOKING_TIME = 7200000; // 2h

    protected final Object lock;

    public final static String PREFIX = "doc-";


    public DocumentEntity(int number, String title, String state, int idSubscriber) {
        this.number = number;
        this.title = title;
        this.state = state;
        this.idSubscriber = idSubscriber;
        this.lock = new Object();

        if (this.idSubscriber != 0) {
            EntityUtils<SubscriberEntity> su         = new EntityUtils<>(SubscriberEntity.class);
            SubscriberEntity              subscriber = su.getEntityById(this.idSubscriber);

            if (this.state.equals("Borrowed")) {
                this.borrower = subscriber;
            }
            if (this.state.equals("Booked")) {
                this.booker = subscriber;
            }
        }
    }

    private static class EndOfBooking extends TimerTask {
        private DocumentEntity document;

        public EndOfBooking(DocumentEntity doc) {
            this.document = doc;
        }

        @Override
        public void run() {
            System.out.println("reset state :)");
            this.document.resetBooker();
        }
    }

    public int getNumber() {
        return number;
    }

    public String getIdentifier() {
        return PREFIX + this.getNumber();
    }

    public String getTitle() {
        return this.title;
    }

    public String getState() {
        return this.state;
    }

    public ISubscriber getBorrower() {
        synchronized (lock) {
            return borrower;
        }
    }

    public ISubscriber getBooker() {
        synchronized (lock) {
            return booker;
        }
    }

    public ISubscriber getSubscriber() {
        return (ISubscriber) DataManager.get(SubscriberEntity.PREFIX + idSubscriber);
    }

    public void setBorrower(ISubscriber subscriber) throws RestrictionException {
        synchronized (lock) {
            if (borrower != null || (booker != null && booker != subscriber))
                throw new RestrictionException();

            this.borrower = subscriber;
            this.booker = null;
            this.idSubscriber = subscriber.getNumber();
            this.state = "Borrowed";
        }
    }

    public void setBooker(ISubscriber subscriber) throws RestrictionException {
        synchronized (lock) {
            if (borrower != null || booker != null)
                throw new RestrictionException();

            this.booker = subscriber;
            this.state = "Booked";
            this.idSubscriber = subscriber.getNumber();
            this.timerBooking = new Timer();
            this.timerBooking.schedule(new EndOfBooking(this), BOOKING_TIME);
        }
    }

    private void resetBooker() {
        this.booker = null;
        this.idSubscriber = null;
    }

    public void returnDocument() {
        synchronized (lock) {
            this.borrower = null;
            this.idSubscriber = null;
            this.state = "Available";
        }
    }

    public void save() throws SQLException {
        DocumentModel<DocumentEntity> documentModel = new DocumentModel<>();

        documentModel.save(this);
    }

    @Override
    public String toString() {
        String state = switch (this.state) {
            case "Available" -> "Libre";
            case "Booked" -> "Réservé";
            case "Borrowed" -> "Emprunté";
            default -> "Ce document est la propriété du grand Wakan Tanka.";
        };

        return this.number + " - " + this.title + " (" + state + ")";
    }
}
