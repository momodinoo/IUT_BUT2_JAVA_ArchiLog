package app.client;

import app.client.managers.ClientManager;

import java.io.IOException;

//TODO Application Part.

public class Main {


    public static void main(String[] args) {


        try {
            ClientManager client;

                client = new ClientManager(args);
            while (true) {
                client.getSocketManager().setup();
                client.getSocketManager().sendLine();

                String serverResponse = client.getSocketManager().readServerLine();

                if (serverResponse.equals("stop")) break;
                System.out.println("hihi");
            }

            client.getSocketManager().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}