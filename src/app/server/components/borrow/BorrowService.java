package app.server.components.borrow;

import libs.server.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class BorrowService extends Service {

    public BorrowService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {

        String r = this.wakanTTP.read();
        this.wakanTTP.send(r);

    }
}
