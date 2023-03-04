package app.server.components.borrow;

import libs.server.Server;

import java.io.IOException;

public class BorrowServer extends Server {

    private static final int BORROW_PORT = 4000;

    public BorrowServer() throws IOException {
        super(BorrowService.class, BORROW_PORT);
    }
}
