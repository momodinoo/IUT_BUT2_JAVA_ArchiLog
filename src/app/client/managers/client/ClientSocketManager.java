package app.client.managers.client;

import libs.wakanttp.WakanTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketManager {


    private final static int    DEFAULT_PORT = 3000;
    private final static String DEFAULT_HOST = "localhost";

    private final WakanTTP wakanTTP;

    private final Socket socket;

    public ClientSocketManager() throws IOException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public ClientSocketManager(int port) throws IOException {
        this(DEFAULT_HOST, port);
    }

    public ClientSocketManager(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.wakanTTP = new WakanTTP(this.socket);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public WakanTTP getProtocol() {
        return this.wakanTTP;
    }


    public void close() throws IOException {
        this.getProtocol().close();
        this.getSocket().close();
    }
}
