package app.server.components.borrow;

import libs.server.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BorrowService extends Service {

    public BorrowService(Socket socket) {
        super(socket);
    }

    @Override
    public void finalize() throws Throwable {
        //do nothing.
    }

    //Todo review that
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.getClient().getOutputStream(), true);
            out.println("Hello World!");
        } catch (IOException ignored) {
        }
    }
}
