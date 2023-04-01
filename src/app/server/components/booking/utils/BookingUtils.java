package app.server.components.booking.utils;

import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;
import app.server.managers.database.DataManager;

import java.util.ArrayList;

public class BookingUtils {

    public static ArrayList<IEntity> getAllDocuments() {
        return DataManager.getDocuments();
    }

}
