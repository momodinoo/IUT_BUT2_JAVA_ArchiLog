package app.server.models.entities;

public interface IDocument {

    int getNumber();

    /**
     * @return null if not reserved or borrowed.
     */
    ISubscriber getBorrower();

    /**
     * @return null if not reserved or borrowed.
     */
    ISubscriber GetBooker();


    /**
     * /!\ Need not be reserved or borrowed
     */
    void setReservation(ISubscriber subscriber);

    /**
     * /!\ Need to be free or reserved by the subscriber who comes to borrow
     */
    void setBook(ISubscriber subscriber);

    /**
     * Return Document or reservation cancellation
     */
    void returnBook();

}
