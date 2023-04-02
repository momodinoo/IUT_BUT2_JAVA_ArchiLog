package app.server.components.returns.utils;

import app.server.entities.DocumentEntity;
import app.server.utils.EntityUtils;
import app.server.utils.Utils;
import libs.wakanttp.WakanTemplate;

import java.io.IOException;

public class  ReturnReturnsService {

    public static void send(WakanTemplate wakanTTP) throws IOException {

        DocumentEntity document = chooseDocument(wakanTTP);

        if(document.getBorrower() != null) {
            document.returnDocument();
            wakanTTP.send("Le document \"" + document.getTitle() + "\" a bien été retourné.");
            return;
        }

        wakanTTP.send("Le document ne peut pas être retourné.");
    }

    private static DocumentEntity chooseDocument(WakanTemplate wakanTTP) throws IOException {

        String         documentId = wakanTTP.read();
        DocumentEntity document;

        EntityUtils<DocumentEntity> su = new EntityUtils<>(DocumentEntity.class);

        while (!Utils.isNumeric(documentId) || (document = su.getEntityById(Integer.parseInt(documentId))) == null) {
            String messageError = "Numéro de document incorrect, veuillez réessayer." + System.lineSeparator() + "Entrez le numéro document : ";
            documentId = retry(messageError, wakanTTP);
        }

        return document;
    }


    private static String retry(String messageError, WakanTemplate wakanTTP) throws IOException {
        wakanTTP.send(messageError);

        return wakanTTP.read();
    }

}
