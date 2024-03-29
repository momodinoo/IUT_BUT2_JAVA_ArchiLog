package app.server.managers.database;

import app.server.components.booking.BookingServer;
import app.server.components.borrow.BorrowServer;
import app.server.components.returns.ReturnServer;
import app.server.entities.DocumentEntity;
import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;
import app.server.managers.server.ServerManager;
import app.server.models.*;
import libs.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataFactory {

    private static final ArrayList<IModel<?>> modelList = new ArrayList<>();

    public static void create() {

        DataFactory.modelList.add(new SubscriberModel<>());
        DataFactory.modelList.add(new DocumentModel<>());
        DataFactory.modelList.add(new DVDModel<>());

        try {
            for (IModel<?> model : modelList) {
                model.send();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void save() {
        try {
            for(IEntity entity : DataManager.getAll()) {
                entity.save();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
