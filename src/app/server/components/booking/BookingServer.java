package app.server.components.booking;

import libs.server.Server;

import java.io.IOException;

public class BookingServer extends Server {

    private static final int BOOKING_PORT = 3000;

    public BookingServer() throws IOException {
        super(BookingService.class, BOOKING_PORT);
    }
}
