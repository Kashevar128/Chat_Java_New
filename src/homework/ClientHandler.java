package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;

    public ClientHandler(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void sendMessage (String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }

    @Override
    public void run() {
        while (running) {
            try {
                String msg = in.readUTF();
                System.out.println("Message from client: " + msg);
                if (msg.equals("/quit")) {
                    out.writeUTF(msg);
                    out.flush();
                    running = false;
                    break;
                }
            }catch (IOException e) {
                System.out.println("Client kicked");
            }
        }
    }
}
