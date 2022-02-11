 package src;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
public class App extends Application {
    static Stage stage = new Stage();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Welcome To Word Travel!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { 
          
          LocalDate myDateObj = LocalDate.now();  
          DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          System.out.println(myDateObj);
        launch();
    }
    static void showAlerterreur(String ch) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText(ch);

		alert.showAndWait();
	}
  
    
}
