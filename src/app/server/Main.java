package app.server;

import app.server.managers.database.DataManager;
import app.server.managers.server.ServerFactory;
import app.server.managers.database.DatabaseConnector;

public class Main {
    private static final String JDCB = "com.mysql.cj.jdbc.Driver";
    private static final String URL  = "jdbc:mysql://sql.freedb.tech:3306/freedb_Archilog?user=freedb_Archilog&password=rnTqGkMSJ!9XsN2";


    public static void main(String[] args) {

        try {
            DatabaseConnector.connect(JDCB, URL);
            DataManager.populate();
            ServerFactory.start();
        } catch (ClassNotFoundException e) {
            System.err.println("Exception found, closing servers...");
            ServerFactory.stop();

            e.printStackTrace();
        }

    }
}
