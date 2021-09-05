package homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ioServer {

    private static ClientHandler handler;

    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен...");
        Thread readThread = new Thread(() -> {
            try {
                Scanner in = new Scanner(System.in);
                while (in.hasNext()) {
                    String msg = in.next();
                    if (handler == null) {
                        System.out.println("You have to wait connection");
                        while (handler == null) {
                            Thread.sleep(500);
                        }
                    }
                    if (handler.isRunning()) {
                        handler.sendMessage(msg);
                        if (msg.equals("/quit")) {
                            handler.setRunning(false);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        });
        readThread.setDaemon(true);
        readThread.start();
        try (ServerSocket server = new ServerSocket(8189)) {
            while (true) {
                Socket socket = server.accept();
                handler = new ClientHandler(socket);
                new Thread(handler).start();
            }
        }
    }
}
