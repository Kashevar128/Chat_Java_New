package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    public TextField login;
    public TextField password;
    public Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void enter() throws IOException {
        boolean auth = MockAuthServiceImpl.getInstance()
                .auth(login.getText(), password.getText());
        if(auth) {
            Parent chat = FXMLLoader.load(getClass().getResource("/chatWork.fxml"));
            stage = new Stage();
            stage.setTitle("Сетевой чат");
            stage.setScene(new Scene(chat));
            stage.setResizable(true);
            stage.show();
            login.getScene().getWindow().hide();
        }else {
            login.setText("WRONG LOGIN OR PASSWORD");
            password.clear();
        }
    }

    public void reg() throws IOException {
        Parent reg = FXMLLoader.load(getClass().getResource("/registration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(reg));
        stage.setResizable(false);
        stage.show();
        login.getScene().getWindow().hide();
    }
}
