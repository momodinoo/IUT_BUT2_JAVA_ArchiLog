package app.client.managers.client;

import app.client.exceptions.NonExistentPortException;

import java.net.Socket;

public class ClientManager {
    private ClientSocketManager clientSocketManager;

    public ClientManager(String[] args) {
        this.manageArguments(args);
    }

    private void manageArguments(String[] args) throws NonExistentPortException {
        this.clientSocketManager = ClientArguments.manageArgument(args);
    }

    public ClientSocketManager getSocketManager() {
        return this.clientSocketManager;
    }
}
