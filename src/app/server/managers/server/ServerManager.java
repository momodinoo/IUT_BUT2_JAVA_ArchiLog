package app.server.managers.server;

import libs.server.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class ServerManager {

    public static void startServer(Class<? extends Server> serverClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Server server = serverClass.getDeclaredConstructor().newInstance();
        new Thread(server).start();
        System.out.println("Server launched on the port " + server.getPort());
    }

    public static void stopServer(Class<? extends Server> serverClass) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Server s = serverClass.getDeclaredConstructor().newInstance();
        s.getListenSocket().close();
    }
}
