package app.server.components.borrow;

import app.server.components.borrow.utils.SelectBookBorrowService;
import app.server.components.borrow.utils.WelcomeBorrowService;
import libs.server.Service;

import java.io.IOException;
import java.net.Socket;

public class BorrowService extends Service {

    public BorrowService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {

        this.getProtocol().read();

        WelcomeBorrowService.send(this.getProtocol());
        SelectBookBorrowService.send(this.getProtocol());
    }
}
