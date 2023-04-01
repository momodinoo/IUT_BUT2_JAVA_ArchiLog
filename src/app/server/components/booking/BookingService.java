package app.server.components.booking;

import app.server.components.booking.utils.SelectBookBookingService;
import app.server.components.booking.utils.WelcomeBookingService;
import libs.server.Service;

import java.io.IOException;
import java.net.Socket;

public class BookingService extends Service {

    public BookingService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {
        this.wakanTTP.read();

        WelcomeBookingService.send(this.wakanTTP);
        SelectBookBookingService.send(this.wakanTTP);
    }
}
