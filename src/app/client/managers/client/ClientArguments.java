package app.client.managers.client;

import app.client.exceptions.NonExistentPortException;

public class ClientArguments {

    public static ClientSocketManager manageArgument(String[] args) throws NonExistentPortException {

        return switch (args.length) {
            case 1 ->  new ClientSocketManager(Integer.parseInt(args[0]));
            case 2 -> new ClientSocketManager(args[0], Integer.parseInt(args[1]));
            default -> new ClientSocketManager();
        };

    }

}
