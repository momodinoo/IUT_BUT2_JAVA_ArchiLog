package app.server;

import app.server.managers.database.DataManager;
import app.server.managers.server.ServerFactory;
import app.server.managers.database.DatabaseConnector;
import app.server.managers.database.DatabaseManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public class Main {
    private static final String JDCB = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_Archilog?user=freedb_Archilog&password=rnTqGkMSJ!9XsN2";


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {

        DatabaseConnector.connect(JDCB, URL);
        ServerFactory.start();

        DataManager.populate();

        DataManager.print();

        Thread.sleep(1000);
        ServerFactory.stop();

        // test
        /*try {
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
        }*/
    }
}
