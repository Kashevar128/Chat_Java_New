package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Cont {
    public TextField text;
    public WebView in;

    public void enter(ActionEvent actionEvent) {
        WebEngine w = in.getEngine();
        w.loadContent(text.getText());
        w.load
    }
}
