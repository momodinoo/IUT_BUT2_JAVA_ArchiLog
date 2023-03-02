package server;

import libs.server.Server;

import java.io.IOException;

public class Main {
      private final static int PORT = 1234;
      public static void main(String[] args) {

            try {
                  new Thread(new Server(PORT)).start();
                  System.out.println("Serveur lance sur le port " + PORT);


            } catch (IOException e) {
                  System.err.println("Pb lors de la création du serveur : " + e);
            }
      }
}
