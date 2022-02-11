package src ;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class controlerwebView implements Initializable {

    @FXML
    private WebView webView;
    @FXML private WebEngine webengine=null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        webengine=webView.getEngine();
        webengine.load("file:///G:/TheEvent/index.html");
    }

}
