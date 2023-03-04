package app.server.managers;

import app.server.components.booking.BookingServer;
import app.server.components.borrow.BorrowServer;
import app.server.components.returns.ReturnServer;
import libs.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServerFactory {
    public static void launchServers() {
        ArrayList<Class<? extends Server>> serverList = new ArrayList<>();

        serverList.add(BookingServer.class);
        serverList.add(BorrowServer.class);
        serverList.add(ReturnServer.class);

        for(Class<? extends Server> server : serverList) {
            try {
                ServerManager.startServer(server);
            }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.err.println("Error on starting servers " + e );
            }
        }


    }
}
