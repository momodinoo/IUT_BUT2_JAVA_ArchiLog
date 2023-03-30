package app.server.managers.database;

import app.server.components.booking.BookingServer;
import app.server.components.borrow.BorrowServer;
import app.server.components.returns.ReturnServer;
import app.server.managers.server.ServerManager;
import app.server.models.AbstractModel;
import app.server.models.DocumentModel;
import app.server.models.IModel;
import libs.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataFactory {

    public static void create() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ArrayList<Class<? extends IModel>> modelList = new ArrayList<>();

        modelList.add(DocumentModel.class);

        try {
            for (Class<? extends IModel> model : modelList) {
                model.getDeclaredConstructor().newInstance().send();
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }

    }

}
