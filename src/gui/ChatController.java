package gui;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatController {
    @FXML
    public ListView output;
    @FXML
    public TextField input;

    public synchronized void send(ActionEvent actionEvent) throws IOException {
        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            protected HBox call() throws Exception {
                System.out.println(input.getText());
                input.clear();
                Label label = new Label();
                label.setText(input.getText());
                HBox x = new HBox();
                x.getChildren().addAll(label);
                return x;
            }
        };
       yourMessages.setOnSucceeded(event ->
               output.getItems().add(yourMessages.getValue())
       );
        Thread t  = new Thread(yourMessages);
        t.setDaemon(true);
        t.start();


    }
}
