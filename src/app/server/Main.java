package app.server;

import app.server.managers.server.ServerFactory;
import app.server.models.DatabaseConnector;
import app.server.models.DatabaseManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Main {
    private static final String JDCB = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_Archilog?user=freedb_Archilog&password=rnTqGkMSJ!9XsN2";


    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {

            DatabaseConnector.connect(JDCB, URL);
            ServerFactory.start();
            Thread.sleep(1000);
            ServerFactory.stop();

            // test
        try {
            ResultSet res = DatabaseManager.execute("SELECT * FROM document");
            ResultSetMetaData rsmd = res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (res.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = res.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                }
                System.out.println("");
            }

            // fin test

            DatabaseManager.close();
            System.out.println("Connection to database closed");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
