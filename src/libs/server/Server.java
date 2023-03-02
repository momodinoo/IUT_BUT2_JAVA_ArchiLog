package libs.server;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.lang.reflect.InvocationTargetException;

public abstract class Server implements Runnable {
      private ServerSocket listenSocket;
      private Class<? extends Service> serviceClass;

      public Server(Class<? extends Service> serviceClass, int port) throws IOException {
            listenSocket = new ServerSocket(port);
            this.serviceClass = serviceClass;
      }

      public void run() {
            try {
                  while (true) {
                        serviceClass.getConstructor(Socket.class).newInstance(listenSocket).start();
                  }
            } catch (NoSuchMethodException e) {

                  try {
                        this.listenSocket.close();
                  } catch (IOException ignored) {
                  }

                  System.err.println("Problem on the listening port : " + e);

            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                  throw new RuntimeException(e);
            }
      }

      //TODO edit finalize method
      protected void finalize() throws Throwable {
            try {
                  this.listenSocket.close();
            } catch (IOException e1) {
            }
      }
}
