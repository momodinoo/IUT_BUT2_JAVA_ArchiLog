package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//TODO Application Part.

// notes du prof
// faire une classe client avec comme seul attribut un port (qui servivra à rediriger vers le bon service)
// au final on devra lui rendre un .bat type java.lauchClient("3000"); (faire une factory ? pour un seul client ptetre uselss)
// puis quand on lancera le bat ça redirigera vers resa ducoup


public class Main {
    private final static int PORT = 3000;
    private final static String HOST = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        String line;

        try {
            System.out.println("Bienvenue sur le service de médiathèque de Morgane et Tony ! \n De quel service avez-vous besoin ?\n - 1 : Réservation d'un document\n - 2 : Emprunt d'un document \n - 3 : Retour d'un document \n - 4 : Quitter");
            socket = new Socket(HOST, PORT);

            BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);

            // keyborad input - var to get the menu result (number from 1 to 4) and will start the service
            // /!\ - choice 4 is not a service, it'll just stop the whole client app
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            line = input.readLine();

            // send the number to the server(s ?)
            sOut.println(line);

            //TODO management of the input (redirect to the right service/server)
            // inside a factory ? or here ?

            // show server answer
            line = sIn.readLine();
            System.out.println("Server answer : " + line);
            socket.close();

        } catch (IOException e) {
            System.err.println(e);
        }
        // close socket
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) {
        }
    }
}