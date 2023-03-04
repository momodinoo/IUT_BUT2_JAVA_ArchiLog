package app.server.managers;

import libs.server.Server;

import java.lang.reflect.InvocationTargetException;

public class ServerManager {

    public static void startServer(Class<? extends Server> serverClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Server server = serverClass.getDeclaredConstructor().newInstance();
        new Thread(server).start();
        System.out.println("Server launched on the port " + server.getPort());
    }

}
