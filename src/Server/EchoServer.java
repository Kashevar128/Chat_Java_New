package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {

    public EchoServer() {
        try(ServerSocket server = new ServerSocket(8189)) {
            System.out.println("Server started!");
            while (true) {
                System.out.println("Server is waiting connection.");
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
                System.out.println("Client accepted!");
                System.out.println("Client info: " + socket.getInetAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
