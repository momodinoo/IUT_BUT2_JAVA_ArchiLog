package app.server.components.booking.utils.step;

import app.server.components.booking.utils.BookingUtils;
import app.server.entities.DocumentEntity;
import app.server.entities.SubscriberEntity;
import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.ISubscriber;
import app.server.exceptions.RestrictionException;
import app.server.utils.EntityUtils;
import libs.wakanttp.WakanTemplate;

import java.io.IOException;
import java.util.Arrays;

public class SelectBookBookingService {

    public static void send(WakanTemplate wakanTTP) throws IOException {

        SubscriberEntity subscriber = SelectBookBookingService.chooseClient(wakanTTP);
        wakanTTP.send("Client choisi : " + subscriber.getName());

        DocumentEntity document = SelectBookBookingService.chooseDocument(wakanTTP);
        wakanTTP.send("Document choisi : " + document.getTitle());



        document.setBooker(subscriber); //TODO synchronize & timer (de 2h)
    }

    private static SubscriberEntity chooseClient(WakanTemplate wakanTTP) throws IOException {

        String   subscriberId = wakanTTP.read();
        SubscriberEntity subscriber;

        EntityUtils<SubscriberEntity> su = new EntityUtils<>(SubscriberEntity.class);

        while (!BookingUtils.isNumeric(subscriberId) || (subscriber = su.getEntityById(Integer.parseInt(subscriberId))) == null) {
            String messageError = "Numéro de client incorrect, veuillez réessayer." + System.lineSeparator() + "Entrez votre numéro client : ";
            subscriberId = retry(messageError, wakanTTP);
        }

        return subscriber;
    }

    private static DocumentEntity chooseDocument(WakanTemplate wakanTTP) throws IOException {

        String         documentId = wakanTTP.read();
        DocumentEntity document;

        EntityUtils<DocumentEntity> su = new EntityUtils<>(DocumentEntity.class);

        while (!BookingUtils.isNumeric(documentId) || (document = su.getEntityById(Integer.parseInt(documentId))) == null) {
            String messageError = "Numéro de document incorrect, veuillez réessayer." + System.lineSeparator() + "Entrez votre numéro client : ";
            documentId = retry(messageError, wakanTTP);
        }

        if(document.getBooker() != null) {
            wakanTTP.send("Livre déjà réservé... Wakan Tanka refuse votre réservation.");
            wakanTTP.close();
            throw new RestrictionException();
        }

        return document;
    }


    private static String retry(String messageError, WakanTemplate wakanTTP) throws IOException {
        wakanTTP.send(messageError);

        return wakanTTP.read();
    }

}
