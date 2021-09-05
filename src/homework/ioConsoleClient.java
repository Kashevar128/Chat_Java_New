package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ioConsoleClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Thread writeThread = new Thread(() -> {
                Scanner inS = new Scanner(System.in);
                try {
                    while (inS.hasNext()) {
                        String msg = inS.next();
                        out.writeUTF(msg);
                        out.flush();
                        if (msg.equals("/quit")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println("Message from server: " + msg);
                        if (msg.equals("/quit")) {
                            System.out.println("Program finished correctly");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            writeThread.setDaemon(true);
            writeThread.start();
            readThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
