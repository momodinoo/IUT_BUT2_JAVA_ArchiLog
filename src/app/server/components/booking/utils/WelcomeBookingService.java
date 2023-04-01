package app.server.components.booking.utils;

import app.server.components.booking.utils.BookingUtils;
import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;
import app.server.utils.EntityUtils;
import libs.wakanttp.WakanTemplate;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WelcomeBookingService {

    public static void send(WakanTemplate wakanTTP) {

        ArrayList<IDocument> documentList = new EntityUtils<>(IDocument.class).getEntityList();
        StringBuilder        sr           = new StringBuilder();

        sr.append("Bienvenue sur le service Réservation, pour commencer, veuillez sélectionner le document que vous souhaitez :").append(System.lineSeparator());

        for(IDocument entity : documentList) {
            sr.append(entity).append(System.lineSeparator());
        }

        sr.append(System.lineSeparator());
        sr.append("Pour commencer, veuillez entrer votre numéro client : ");

        wakanTTP.send(sr.toString());

    }

}
