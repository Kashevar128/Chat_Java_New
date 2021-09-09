package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ioConsoleClient {  //класс клиента
    public static void main(String[] args) { //основной метод программы клиента
        try {
            Socket socket = new Socket("localhost", 8189); // Создаем сокет, пишем Localhost, имея ввиду данный компьютер, пишем порт 8189
            DataInputStream in = new DataInputStream(socket.getInputStream()); // Извекам исходящий поток из сокета, обертываем классом, конвертирующим сообщения в массив байт
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Тоже самое только с входящим потоком
            Thread writeThread = new Thread(() -> { // Создаем поток для написания сообщений
                Scanner inS = new Scanner(System.in); // Создаем сканер inS с вложенной переменной InputStream in
                try {                                // Блок для обхода ошибок
                    while (inS.hasNext()) { // Цикл работает пока есть на входе следующий токен
                        String msg = inS.next(); // В строковую переменную msg кладем строки из переменной inS с помощью next
                        out.writeUTF(msg); // В поток out кладем переменную msg с сообщением
                        out.flush(); // Сбрасываем всю инфу из буфера
                        if (msg.equals("/quit")) { // Если сообщение равно /quit, делаем прерывание цикла
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Thread readThread = new Thread(() -> { // Создаем поток для чтения сообщений
                try {                             // Блок для обхода ошибок
                    while (true) {              // Цикл для чтений сообщений от сервера
                        String msg = in.readUTF(); // В переменную читаем сообщение из потока in
                        System.out.println("Message from server: " + msg); // Пишем в консоль сообщение от сервера.
                        if (msg.equals("/quit")) { // если сообщение равно /quit
                            System.out.println("Program finished correctly"); // Пишем что программа завершена корректно
                            break; // Делаем прерываение цикла
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Server was broken"); // Если сервер пропал, пишем в консоль, что сервер был уничтожен
                }
            });
            writeThread.setDaemon(true); // Делаем пишущий поток демоном
            writeThread.start(); // Запускаем пишущий поток
            readThread.start(); // Запускаем читающий поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
