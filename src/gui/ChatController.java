package gui;


import javafx.concurrent.Task;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatController {
    @FXML
    public ListView<HBox> output;
    @FXML
    public TextField input;


    public void send() throws IOException {
        if (!filter(input.getText())) {
            input.clear();
            return;
        }
        addToChat(input.getText());
        input.clear();
    }

    public void addToChat(String msg) {
        LabelChat labelChat = new LabelChat(msg);
        Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("img/544_oooo.plus.png")));
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(60);
        profileImage.setFitWidth(60);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(profileImage, labelChat);
        HBox.setMargin(profileImage, new Insets(10, 10, 10,10));
        HBox.setMargin(labelChat, new Insets(20, 10, 20, 10));
        output.getItems().add(hBox);
        int index = output.getSelectionModel().getSelectedIndex();
        output.getSelectionModel().clearSelection(index);
    }

    private static boolean filter (String msg) {
        boolean flag = true;
        if(msg.equals("")) flag = false;
        for (int i = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == ' ') flag = false;
        }
        return flag;
    }
}
