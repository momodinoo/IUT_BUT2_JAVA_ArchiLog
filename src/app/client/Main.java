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

            client.getProtocol().send("welcome");
            System.out.println(client.getProtocol().read());

            while (true) {

                client.getProtocol().setupCommunication();

                BufferedReader content = client.getSocketManager().getInput();

                client.getProtocol().send(content.readLine());
                String response = client.getProtocol().read();

                if (response.equals("stop")) break;

                System.out.println(response);

            }

            client.getSocketManager().close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}