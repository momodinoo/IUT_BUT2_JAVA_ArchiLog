package app.server.components.borrow;

import libs.server.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class BorrowService extends Service {

    public BorrowService(Socket socket) {
        super(socket);
    }

    //Todo review that
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.getClient().getInputStream()));
            PrintWriter out = new PrintWriter(this.getClient().getOutputStream(), true);
            String line = in.readLine();

            if (Objects.equals(line, "2"))
                out.println("Bien sur le service d'emprunt");

        } catch (IOException ignored) {}
    }
}
