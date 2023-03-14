package app.client;

import app.client.exceptions.NonExistentPortException;
import app.client.managers.client.ClientManager;

import java.io.IOException;

//TODO Application Part.

public class Main {


    public static void main(String[] args) {

        ClientManager client;

        try {
            client = new ClientManager(args);

            while (true) {
                client.getSocketManager().setup();
                client.getSocketManager().sendLine();

                String serverResponse = client.getSocketManager().readServerLine();

                if (serverResponse.equals("stop")) break;
                System.out.println(serverResponse);
            }

            client.getSocketManager().close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NonExistentPortException ce) {
            System.err.println("Connection refused.");
            System.exit(NonExistentPortException.EXIT_CODE);
        }
    }
}