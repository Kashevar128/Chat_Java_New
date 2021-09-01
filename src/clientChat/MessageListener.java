package clientChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageListener implements Runnable {
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;

    public MessageListener(Socket socket) throws IOException {
        this.socket = socket;
        this.is = new DataInputStream(socket.getInputStream());
        this.os = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        while (true) {
            try {
                String messageFromServer = is.readUTF();
                System.out.println("Received from server: " + messageFromServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
