package libs.server;

import libs.wakanttp.WakanTTP;
import libs.wakanttp.WakanTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public abstract class Service implements Runnable {

    protected final Socket client;
    protected final WakanTTP wakanTTP;

    public Service(Socket socket) throws IOException {
        this.client = socket;
        this.wakanTTP = new WakanTTP(this.client);
    }

    protected Socket getClient() {
        return this.client;
    }

    protected WakanTemplate getProtocol() {
        return this.wakanTTP;
    }

    abstract protected void execute() throws IOException;

    @Override
    public void run() {
        try {
            while (true) {
                this.wakanTTP.setupCommunication();
                execute();
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        (new Thread(this)).start();
    }
}