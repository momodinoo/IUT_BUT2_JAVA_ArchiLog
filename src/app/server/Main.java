package app.server;

import app.server.managers.database.DataFactory;
import app.server.managers.database.DataManager;
import app.server.managers.server.ServerFactory;
import app.server.managers.database.DatabaseConnector;

public class Main {
    private static final String JDCB = "com.mysql.cj.jdbc.Driver";
    private static final String URL  = "jdbc:mysql://sql2.minestrator.com:3306/minesr_3MQ0ZKy7?user=minesr_3MQ0ZKy7&password=GowuLfIqL2tFQNzS";


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


        Runtime.getRuntime().addShutdownHook(new Thread(DataFactory::save, "Shutdown-thread"));

    }
}
