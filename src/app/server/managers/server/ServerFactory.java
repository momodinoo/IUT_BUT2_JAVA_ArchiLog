package app.server.managers.server;

import app.server.components.booking.BookingServer;
import app.server.components.borrow.BorrowServer;
import app.server.components.returns.ReturnServer;
import app.server.managers.database.DataFactory;
import libs.server.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServerFactory {

    public static void start() {
        ArrayList<Class<? extends Server>> serversList = new ArrayList<>();

        serversList.add(BookingServer.class);
        serversList.add(BorrowServer.class);
        serversList.add(ReturnServer.class);

        for (Class<? extends Server> server : serversList) {
            try {
                ServerManager.start(server);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                System.err.println("Error on starting server " + server);
                e.printStackTrace();
            }
        }
    }

    public static void stop() {
        try {
            DataFactory.save();
            ServerManager.stopAll();
        } catch (IOException e) {
            System.err.println("Error on stopping servers");
            e.printStackTrace();
        }
    }
}
