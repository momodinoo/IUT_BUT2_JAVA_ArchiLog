package app.server.components.returns;

import libs.server.Server;

import java.io.IOException;

public class ReturnServer extends Server {

    private static final int RETURN_PORT = 5000;

    public ReturnServer() throws IOException {
        super(ReturnService.class, RETURN_PORT);
    }
}
