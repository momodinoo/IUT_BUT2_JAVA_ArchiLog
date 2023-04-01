package app.client.managers.client;

import libs.wakanttp.WakanTemplate;

import java.io.IOException;

public class ClientManager {
    private ClientSocketManager clientSocketManager;

    public ClientManager(String[] args) throws IOException {
        this.manageArguments(args);
    }

    private void manageArguments(String[] args) throws IOException {
        this.clientSocketManager = ClientArguments.manageArgument(args);
    }

    public ClientSocketManager getSocketManager() {
        return this.clientSocketManager;
    }

    public WakanTemplate getProtocol() {
        return this.getSocketManager().getProtocol();
    }
}
