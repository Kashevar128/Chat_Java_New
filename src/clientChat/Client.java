package clientChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Socket socket = new Socket("localhost",8189);
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        while (in.hasNext()) {
            String message = in.next();
            os.writeUTF(message);
            System.out.println(is.readUTF());
        }
    }
}
