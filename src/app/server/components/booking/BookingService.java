package app.server.components.booking;

import libs.server.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class BookingService extends Service {

    public BookingService(Socket socket) {
        super(socket);
    }

    //Todo review that
    @Override
    public void run() {
        try {

            while (true) {
                PrintWriter out = new PrintWriter(this.getClient().getOutputStream(), true);

                BufferedReader in   = new BufferedReader(new InputStreamReader(this.getClient().getInputStream()));
                String         line = in.readLine();

                out.println(line);
            }

            //TODO  afficher tous les docuements disponibles
        } catch (IOException ignored) {
        }
    }
}
