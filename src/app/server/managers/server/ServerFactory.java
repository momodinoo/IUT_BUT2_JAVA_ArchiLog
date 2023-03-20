package app.server.managers.server;

import app.server.components.booking.BookingServer;
import app.server.components.borrow.BorrowServer;
import app.server.components.returns.ReturnServer;
import libs.server.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServerFactory {
    private static ArrayList<Class<? extends Server>> serverList = new ArrayList<>();

    public static void launchServers() {
        serverList.add(BookingServer.class);
        serverList.add(BorrowServer.class);
        serverList.add(ReturnServer.class);

        for (Class<? extends Server> server : serverList) {
            try {
                ServerManager.startServer(server);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                System.err.println("Error on starting server " + server + e);
            }
        }
    }

    public static void stopServers() {
        for (Class<? extends Server> server : serverList) {
            try {
                ServerManager.stopServer(server);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | IOException e) {
                System.err.println("Error on stopping server " + server + e);
            }
        }
    }
}
