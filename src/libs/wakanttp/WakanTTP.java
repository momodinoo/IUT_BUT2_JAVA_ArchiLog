package libs.wakanttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WakanTTP extends WakanTemplate {

    private final String CHAR_REPLACEMENT = "##";

    public WakanTTP(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public String encode(String data) {
        if(data != null) {
            return data.replaceAll(System.lineSeparator(), CHAR_REPLACEMENT);
        }
        return "";
    }

    @Override
    public String decode(String data) {
        if(data != null) {
            return data.replaceAll(CHAR_REPLACEMENT, System.lineSeparator());
        }
        return null;
    }
}
