package libs.server;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.lang.reflect.InvocationTargetException;

public abstract class Server implements Runnable {
    private final int                      port;
    private final ServerSocket             listenSocket;
    private final Class<? extends Service> serviceClass;

    public Server(Class<? extends Service> serviceClass, int port) throws IOException {
        this.port = port;
        this.serviceClass = serviceClass;
        this.listenSocket = new ServerSocket(this.port);
    }

    public Server(Class<? extends Service> serviceClass) throws IOException {
        this.port = 1111;
        this.serviceClass = serviceClass;
        this.listenSocket = new ServerSocket(this.port);
    }

    public int getPort() {
        return this.port;
    }

    public void run() {
        try {
            while (true) {
                this.serviceClass.getConstructor(Socket.class).newInstance(listenSocket.accept()).start();
            }
        } catch (NoSuchMethodException e) {

            try {
                this.listenSocket.close();
            } catch (IOException ignored) {
            }

            System.err.println("Problem on the listening port : " + e);

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO edit finalize method
    protected void finalize() {
        try {
            this.listenSocket.close();
        } catch (IOException ignored) {
        }
    }
}
