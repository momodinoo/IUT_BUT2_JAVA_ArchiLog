package app.server.components.booking.utils;

import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;
import app.server.entities.interfaces.ISubscriber;
import app.server.managers.database.DataManager;

import java.util.ArrayList;

public class BookingUtils {

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

}
