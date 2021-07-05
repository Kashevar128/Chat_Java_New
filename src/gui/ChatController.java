package gui;


import javafx.concurrent.Task;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatController {
    @FXML
    public ListView <HBox> output;
    @FXML
    public TextField input;

    public synchronized void send(ActionEvent actionEvent) throws IOException {
        LabelChat labelChat = new LabelChat(input.getText());
            Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("img/544_oooo.plus.png")));
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(profileImage, labelChat);
            output.getItems().add(hBox);
            input.clear();
    }
}
