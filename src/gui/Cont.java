package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Cont {
    public WebView web;
    public WebEngine webEngine;
    @FXML
    ListView<HBox> list;
    @FXML
    TextField input;

    public void send(ActionEvent actionEvent) {
        Label label = new Label(input.getText());
        Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("img/544_oooo.plus.png")));
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(32);
        profileImage.setFitWidth(32);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(profileImage, label);
        list.getItems().add(hBox);
        input.clear();
    }

    public void send2(ActionEvent actionEvent) {
       webEngine =  web.getEngine();
       webEngine.loadContent(input.getText());
        Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("img/544_oooo.plus.png")));
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(32);
        profileImage.setFitWidth(32);
       webEngine.loadContent("img/544_oooo.plus.png");
    }
}
