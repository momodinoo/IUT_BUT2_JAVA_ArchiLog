package app.server.entities.interfaces;

import app.server.exceptions.RestrictionException;

//TODO écrire et lock les méthodes lockables

public interface IDocument extends IEntity {


    /**
     * @return null if not reserved or borrowed.
     */
    ISubscriber getBorrower();

    /**
     * @return null if not reserved or borrowed.
     */
    ISubscriber GetBooker();


    /**
     * /!\ Need to be free (not booked or borrowed)
     */
    void setBorrower(ISubscriber subscriber) throws RestrictionException;

    /**
     * /!\ Need to be free or reserved by the subscriber who comes to borrow
     */
    void setBooker(ISubscriber subscriber) throws RestrictionException;

    /**
     * Return Document or reservation cancellation
     */
    void returnDocument();

}
