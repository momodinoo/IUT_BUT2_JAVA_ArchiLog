package app.server.managers.server;

import libs.server.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServerManager {

    private static final ArrayList<Server> serversList = new ArrayList<>();

    public static void start(Class<? extends Server> serverClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Server server = serverClass.getDeclaredConstructor().newInstance();

        serversList.add(server);

        new Thread(server).start();

        System.out.println("Server launched on the port " + server.getPort());
    }

    public static void stopAll() throws IOException {

        for(Server server : serversList) {
            server.stop();
        }

        serversList.clear();
    }
}
