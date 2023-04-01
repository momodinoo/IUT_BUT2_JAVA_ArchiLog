package app.client;

import app.client.managers.client.ClientManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

//TODO Application Part.

public class Main {


    public static void main(String[] args) {

        ClientManager client;

        try {
            client = new ClientManager(args);

            while (true) {

                client.getSocketManager().getProtocol().setupCommunication();

                BufferedReader rd   = new BufferedReader(new InputStreamReader(System.in));
                String         gnee = rd.readLine();

                client.getSocketManager().getProtocol().send(gnee);
                String response = client.getSocketManager().getProtocol().read();

                if (response.equals("stop")) break;

                System.out.println(response);

            }

            client.getSocketManager().close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}