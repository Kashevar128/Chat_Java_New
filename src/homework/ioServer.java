package homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ioServer { //класс сервера для приема и обработки клиентов.

    private static ClientHandler handler; //поле(field) для экземпляра (инстанса) обработчка клиентов (клиентхендлер).

    public static void main(String[] args) throws IOException { //основной метод для запуска программы сервера.
        System.out.println("Сервер запущен..."); //запись в консоль.
        Thread readThread = new Thread(() -> { //создание потока с run внутри в виде лямда-выражения. Поток отвечает за чтение записи в консоли и отправку его через екземпляр обработчика клиентов.
            try {                              //конструкция для обхода ошибок.
                Scanner in = new Scanner(System.in); //Создание экземпляра сканера in, на вход созданного сканера подается переменная in с типом данных InputStream, принадлежащая классу System.
                while (in.hasNext()) { // метод hasNext() смотрит на наличие следущего токена в потоке, если он есть, то цикл работает.
                    String msg = in.next(); // метод next() отвечает за пропуск строковых выражений в строковую переменную msg.
                    if (handler == null) { // если handler пуст
                        System.out.println("You have to wait connection"); // в консоль выводится просьба подождать подключения.
                        while (handler == null) { // цикл работает, пока handler равен нулю.
                            Thread.sleep(500); // в этом цикле поток будет погружаться в сон на 500 млс, после это проверять условие и опять засыпать.
                        }
                    }
                    if (handler.isRunning()) { // если в хэндлере работает поток run
                        handler.sendMessage(msg); //через метод sendMessage хэндлера отправляем сообщение.
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
