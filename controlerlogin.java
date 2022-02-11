package src;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class controlerlogin  implements Initializable {
    @FXML
    private TextField Nom;
    @FXML
    static
    Label compte;

    @FXML
    private PasswordField Psd;

    @FXML
    private Button newcompte;

    @FXML
    private Button sign;
    
   
   
    //check account
 
    @FXML
    void Signin(ActionEvent event) {
        BDconection bd=new BDconection();
        String login =Nom.getText();
        String psd = Psd.getText();
        int check=0;
        try {
             check= bd.checkaccount(login, psd);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("problem bd dans le block check account");
        }
        try {
            if(check!=0) {
                controlerhome.account=login;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene= new Scene(root);
               // Stage stage=new Stage();
                Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
                App.stage.getIcons().add(icon);
                App.stage.setTitle("home!");
                App.stage.setScene(scene);
                App.stage.show();

              
            }
            else{
                App.showAlerterreur("login and password no correct");
        
            }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println("errer here ");
        e.printStackTrace();
    }
   

    }
    @FXML
    void Newaccount(ActionEvent event) {
      
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Scene scene= new Scene(fxmlLoader.load());
            Stage stage1=new Stage();
            Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
            stage1.getIcons().add(icon);
            stage1.setTitle("Signup!");
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("errer signup ");
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
        
    }
   

}
