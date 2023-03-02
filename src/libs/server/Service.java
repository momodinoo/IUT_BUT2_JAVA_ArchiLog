package libs.server;

import java.net.Socket;

public abstract class Service implements Runnable {

      protected final Socket client;

      public Service(Socket socket) {
            this.client = socket;
      }

      public void start() {
            (new Thread(this)).start();
      }

      //TODO edit finialize method
      public abstract void finalize() throws Throwable;

}