package app.client.managers;

import java.io.IOException;
import java.net.Socket;

public class ClientManager {
    private ClientSocketManager    clientSocketManager;

    public ClientManager(String[] args) throws IOException {
        this.manageArguments(args);
    }

    private void manageArguments(String[] args) throws IOException {
        switch (args.length) {
            case 1 -> {
                int port = (Integer.parseInt(args[0]));
                this.clientSocketManager = new ClientSocketManager(port);
            }
            case 2 -> {
                String host = args[0];
                int port = Integer.parseInt(args[1]);
                this.clientSocketManager = new ClientSocketManager(host, port);
            }
            default -> this.clientSocketManager = new ClientSocketManager();
        }
    }

    public ClientSocketManager getSocketManager() {
        return this.clientSocketManager;
    }

    public Socket getSocket() {
        return clientSocketManager.getSocket();
    }
}
