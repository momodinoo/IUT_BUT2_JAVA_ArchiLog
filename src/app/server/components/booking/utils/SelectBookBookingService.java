package app.server.components.booking.utils;

import app.server.entities.DVDEntity;
import app.server.entities.DocumentEntity;
import app.server.entities.SubscriberEntity;
import app.server.exceptions.RestrictionException;
import app.server.models.DVDModel;
import app.server.utils.EntityUtils;
import app.server.utils.Utils;
import libs.wakanttp.WakanTemplate;

import java.io.IOException;

public class SelectBookBookingService {

    public static void send(WakanTemplate wakanTTP) throws IOException {

        SubscriberEntity subscriber = SelectBookBookingService.chooseClient(wakanTTP);
        wakanTTP.send("Client choisi : " + subscriber.getName() + System.lineSeparator() + "Veuillez entrer le numéro du document à réserver :");

        DocumentEntity document = SelectBookBookingService.chooseDocument(wakanTTP);

        DVDModel <DVDEntity> dvdModel = new DVDModel <>();
        DVDEntity            dvd      = dvdModel.getDVDEntityByDocumentId(document.getNumber());

        if (dvd != null && dvd.isForAdults()) {
            if (!Utils.hasAdultAge(subscriber.getDateOfBirth())) {
                wakanTTP.send("Vous n'avez pas l'âge requis pour réserver ce livre.");
                return;
            }
        }

        try {
            document.setBooker(subscriber);
        } catch (RestrictionException re) {
            wakanTTP.send("Ce document est déjà réservé.");
            return;
        }

        //document.save();
        wakanTTP.send("Document réservé : " + document.getTitle());
    }

    private static SubscriberEntity chooseClient(WakanTemplate wakanTTP) throws IOException {

        String           subscriberId = wakanTTP.read();
        SubscriberEntity subscriber;

        EntityUtils <SubscriberEntity> su = new EntityUtils <>(SubscriberEntity.class);

        while (!Utils.isNumeric(subscriberId) || (subscriber = su.getEntityById(Integer.parseInt(subscriberId))) == null) {
            String messageError = "Numéro de client incorrect, veuillez réessayer." + System.lineSeparator() + "Entrez votre numéro client : ";
            subscriberId = retry(messageError, wakanTTP);
        }

        return subscriber;
    }

    private static DocumentEntity chooseDocument(WakanTemplate wakanTTP) throws IOException {

        String         documentId = wakanTTP.read();
        DocumentEntity document;

        EntityUtils <DocumentEntity> su = new EntityUtils <>(DocumentEntity.class);

        while (!Utils.isNumeric(documentId) || (document = su.getEntityById(Integer.parseInt(documentId))) == null) {
            String messageError = "Numéro de document incorrect, veuillez réessayer." + System.lineSeparator() + "Entrez le numéro du document : ";
            documentId = retry(messageError, wakanTTP);
        }

        return document;
    }


    private static String retry(String messageError, WakanTemplate wakanTTP) throws IOException {
        wakanTTP.send(messageError);

        return wakanTTP.read();
    }

}
