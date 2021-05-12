package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {
    public TextArea output;

    public TextField input;

    public void send() {
        output.appendText(input.getText() + "\n");
        input.clear();
    }
}
