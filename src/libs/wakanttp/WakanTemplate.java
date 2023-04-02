package libs.wakanttp;

import java.io.*;
import java.net.Socket;

public abstract class WakanTemplate {

    private Socket         socket;
    private PrintWriter    out;
    private BufferedReader in;

    public WakanTemplate(Socket socket) throws IOException {
        this.socket = socket;
        setupCommunication();
    }

    abstract public String encode(String data);

    abstract public String decode(String data);

    public void setupCommunication() throws IOException {
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public void send(String data) {
        out.println(this.encode(data));
    }

    public String read() throws IOException {
        return this.decode(in.readLine());
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }

}
