package app.server;

import app.server.managers.server.ServerFactory;

public class Main {
      public static void main(String[] args) throws InterruptedException {
            ServerFactory.start();
            Thread.sleep(1000);
            ServerFactory.stop();
      }
}
