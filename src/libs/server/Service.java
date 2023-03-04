package libs.server;

import java.io.IOException;
import java.net.Socket;

public abstract class Service implements Runnable {

    protected final Socket client;

    public Service(Socket socket) {
        this.client = socket;
    }

    protected Socket getClient() {
        return this.client;
    }

    protected void closeClient() {
        try {
            this.getClient().close();
        } catch (IOException ignored) {
        }
    }

    public void start() {
        (new Thread(this)).start();
    }

    //TODO edit finialize method
    public abstract void finalize() throws Throwable;

}