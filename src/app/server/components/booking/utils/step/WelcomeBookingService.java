package app.server.components.booking.utils.step;

import app.server.components.booking.utils.BookingUtils;
import app.server.entities.interfaces.IEntity;
import libs.wakanttp.WakanTemplate;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WelcomeBookingService {

    public static void send(WakanTemplate wakanTTP) {

        ArrayList<IEntity> documentList = BookingUtils.getAllDocuments();
        StringBuilder sr = new StringBuilder();

        sr.append("Bienvenue sur le service Réservation, pour commencer, veuillez sélectionner le document que vous souhaitez :").append(System.lineSeparator());

        for(IEntity entity : documentList) {
            sr.append(entity).append(System.lineSeparator());
        }

        wakanTTP.send(sr.toString());

    }

}
