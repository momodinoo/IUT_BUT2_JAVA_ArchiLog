package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//TODO Application Part.

public class Main {
    private final static int    PORT = 3000;
    private final static String HOST = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        String line;

        try {
            socket = new Socket(HOST, PORT);

            BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            line = sin.readLine();

            System.out.println("Réponse serveur : " + line);
            socket.close();

        } catch (IOException e) {
            System.err.println(e);
        }
        // Refermer dans tous les cas la socket
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) {
        }
    }
}