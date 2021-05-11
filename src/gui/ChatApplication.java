package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent chat = FXMLLoader.load(getClass().getResource("/chat.fxml"));
        primaryStage.setTitle("Сетевой чат");
        primaryStage.setScene(new Scene(chat));
        primaryStage.show();
    }
}
