package app.server.components.returns;

import app.server.components.booking.utils.SelectBookBookingService;
import app.server.components.booking.utils.WelcomeBookingService;
import app.server.components.returns.utils.ReturnReturnsService;
import app.server.components.returns.utils.WelcomeReturnsService;
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

        this.getProtocol().read();

        WelcomeReturnsService.send(this.getProtocol());
        ReturnReturnsService.send(this.getProtocol());
    }
}