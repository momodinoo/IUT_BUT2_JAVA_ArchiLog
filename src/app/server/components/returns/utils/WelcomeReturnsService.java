package app.server.components.returns.utils;

import app.server.entities.interfaces.IDocument;
import app.server.utils.EntityUtils;
import libs.wakanttp.WakanTemplate;

import java.util.ArrayList;

public class WelcomeReturnsService {

    public static void send(WakanTemplate wakanTTP) {

        wakanTTP.send("Bienvenue sur le service de retour, veuillez entrer le document que vous souhaitez rendre :" + System.lineSeparator());

    }

}
