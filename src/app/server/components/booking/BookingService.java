package app.server.components.booking;

import app.server.components.booking.utils.step.WelcomeBookingService;
import libs.server.Service;

import java.io.IOException;
import java.net.Socket;

public class BookingService extends Service {

    private boolean welcomeMessage = false;

    public BookingService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {

            if(!welcomeMessage) {
                WelcomeBookingService.send(this.wakanTTP);
                welcomeMessage = !welcomeMessage;
                return;
            }

            this.wakanTTP.read();
            this.wakanTTP.send("Waiting...");
    }
}
