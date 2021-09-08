package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable { //Класс обработчика клиентов реализующий интерфейс с методом run (Это нужно для задания созданному потоку).

    //поля класса ClientHandler
    private DataInputStream in; //поле входящего потока.
    private DataOutputStream out; //поле исходящего потока.
    private boolean running; // логический флаг, разрещаюший или запрещающий работать циклу в потоке run.

    public ClientHandler(Socket socket) throws IOException { //создаем конструктор обработчика и передаем ему аргумент сокета - сущность содержащая инфу о порте и хосте.
        in = new DataInputStream(socket.getInputStream()); //входящий поток извлекаемый из сокета кладется в переменную in в обертке класса DataInputStream для чтения сообщений в виде массивов байт.
        out = new DataOutputStream(socket.getOutputStream()); //исходящий поток извелаемый из сокета кладется в переменную out в обертке класса DataOutputStream для передачи сообщений в виде массивов байт.
        running = true; //флаг по умолчанию устанавилвается на правду.
    }

    public boolean isRunning() {
        return running;
    } //геттер на running;

    public void setRunning(boolean running) {
        this.running = running;
    } //сеттер на running;

    public void sendMessage (String msg) throws IOException { //метод для отправки сообщений, с вложенным строковым аргументом.
        out.writeUTF(msg); //пишем сообщение в поток out.
        out.flush();//сброс накопленной информации в буфере в поток out, сообщение отправляется получателю.
    }

    @Override // переопределение абстрактного метода run из интерфейса runnable.
    public void run() { //тело метода run, указывает порядок действий дополнительному потоку.
        while (running) { // цикл с булевым флагом.
            try {                           //конструкция для обхода ошибок.
                String msg = in.readUTF(); //читаем из потока входа сообщение в строковую переменную msg.
                System.out.println("Message from client: " + msg); //вывод в консоль сообщения от клиента через переменную msg + приставака "Сообщение от клиента".
                if (msg.equals("/quit")) { // если msg = "/quit" приходит от клиента, то
                    out.writeUTF(msg); // это же сообщение отправляем обратно в поток out клиенту.
                    out.flush(); // сбрасываем буфер.
                    running = false; // булевую переменную переводим на "ложь", так как клиент вышел и поток чтения сообщений от клиента нужно прервать.
                    break; // прерывание цикла while.
                }
            }catch (IOException e) {                   //ошибка потоков входа-выхода,
                System.out.println("Client kicked");   //в случае неправильного завершения работы клиента, пишет: "Клиент выкинут.".
                break; //прерывание цилка while.
            }
        }
    }
}
