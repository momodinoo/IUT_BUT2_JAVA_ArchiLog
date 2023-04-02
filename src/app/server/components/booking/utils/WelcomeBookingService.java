package app.server.components.booking.utils;

import app.server.entities.interfaces.IDocument;
import app.server.utils.EntityUtils;
import libs.wakanttp.WakanTemplate;

import java.util.ArrayList;

public class WelcomeBookingService {

    public static void send(WakanTemplate wakanTTP) {

        ArrayList<IDocument> documentList = new EntityUtils<>(IDocument.class).getEntityList();
        StringBuilder        sr           = new StringBuilder();

        sr.append("Bienvenue sur le service Réservation, voici la liste des documents disponibles :").append(System.lineSeparator());

        for(IDocument entity : documentList) {
            sr.append(entity).append(System.lineSeparator());
        }

        sr.append(System.lineSeparator());
        sr.append("Pour commencer, veuillez entrer votre numéro client : ");

        wakanTTP.send(sr.toString());

    }

}
