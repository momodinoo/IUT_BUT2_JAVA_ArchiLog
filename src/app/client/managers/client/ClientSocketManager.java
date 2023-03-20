package app.client.managers.client;

import app.client.exceptions.NonExistentPortException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketManager {


    private final static int    DEFAULT_PORT = 3000;
    private final static String DEFAULT_HOST = "localhost";

    private final Socket socket;

    private BufferedReader serverInput;
    private PrintWriter    clientInput;

    public ClientSocketManager() throws NonExistentPortException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public ClientSocketManager(int port) throws NonExistentPortException {
        this(DEFAULT_HOST, port);
    }

    public ClientSocketManager(String host, int port) throws NonExistentPortException {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            throw new NonExistentPortException();
        }
    }

    public ClientSocketManager(Socket socket) throws NonExistentPortException {
        this.socket = socket;
    }

    private void setupServerInput() throws IOException {
        this.serverInput = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
    }

    private void setupClientInput() throws IOException {
        this.clientInput = new PrintWriter(this.getSocket().getOutputStream(), true);
    }

    private BufferedReader getInput() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private void closeInputs() throws IOException {
        if (this.serverInput != null) {
            this.serverInput.close();
        }
        if (this.clientInput != null) {
            this.clientInput.close();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setup() throws IOException {
        this.setupServerInput();
        this.setupClientInput();
    }

    public void sendLine() throws IOException {
        BufferedReader input = this.getInput();
        this.clientInput.println(input.readLine());
    }


    public String readServerLine() throws IOException {
        return this.serverInput.readLine();
    }

    public void close() throws IOException {
        this.closeInputs();
        this.getSocket().close();
    }
}
