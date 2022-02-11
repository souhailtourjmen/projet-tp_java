package src;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ControllerPassager {

    @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField pass;

    @FXML
    private TextField prenom;

    @FXML
    private Button register;

    @FXML
    private TextField tel;

    static Passager passager ;
    static  int i =0 ;

    @FXML
    void register(ActionEvent event) throws IOException {
        String req="insert into PASSAGERS(PASSPORT,NOM,PRENOM,TEL,EMAIL,ADRESSE,PASS) values ('"+pass.getText()+"','"+nom.getText()+"','"+prenom.getText()+"','"+tel.getText()+"','"+email.getText()+"','"+adresse.getText()+"','"+pass.getText()+"')";
        BDconection bdcon = new BDconection();
        try {
            i=bdcon.insert(req);
            if(i==0){
                App.showAlerterreur("problem insert ");
                
            }
            else{
                passager=setpass();
            //     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticket.fxml"));
            //     Parent root = fxmlLoader.load();
            //     Scene scene= new Scene(root);
            //    //Stage stage=new Stage();
            //     Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
            //     App.stage.getIcons().add(icon);
            //     App.stage.setTitle("Ticket");
            //     App.stage.setScene(scene);
            //     App.stage.show();
             }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    public  Passager setpass(){
    Passager passager =new Passager(nom.getText(),prenom.getText(),adresse.getText(),tel.getText(),email.getText(),pass.getText());
        return passager;
    }

}
