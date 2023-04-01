package app.server.components.returns;

import libs.server.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ReturnService extends Service {

    public ReturnService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {

        this.wakanTTP.send(this.wakanTTP.read());

    }
}