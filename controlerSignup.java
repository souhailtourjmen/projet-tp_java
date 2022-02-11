package src;

import java.io.IOException;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class controlerSignup {

    @FXML
    private TextField Email;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private Button create;

    @FXML
    private PasswordField psd;
    @FXML
    private TextField Cin;
    
     //insert clients
     @FXML
        public int insertclient() throws ClassNotFoundException, SQLException  {
        
            BDconection bd=new BDconection();
            int cin=Integer.parseInt(Cin.getText()); 
            Client client = new Client(cin, FirstName.getText(), LastName.getText(),Email.getText(),psd.getText());
            String req="insert into client values ('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getEmail()+"','"+client.getPassword()+"',"+client.getCin()+")";
            
               int v,v1=0;
            try {
                
               v1= bd.checkaccount(client.getEmail(),client.getPassword());
               if(v1==0){
                v= bd.insert(req);
                if(v==0){
                    App.showAlerterreur("problem insert "); 
                }
               
            }
            else{
                App.showAlerterreur("compte existe "); 
            }
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return v1;
            

          
         

      }
      static String account ;
     
    @FXML
    void newaccount(ActionEvent event) throws ClassNotFoundException, SQLException {
        
    
         if(insertclient()==0)   {
        try {
            controlerhome.account=Email.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Scene scene= new Scene(fxmlLoader.load());
            Stage stage1=new Stage();
            Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
            stage1.getIcons().add(icon);
            stage1.setTitle("Home!");
            stage1.setScene(scene);
            stage1.show();
           
        } catch (IOException e) {
            
            System.out.println("errer signup ");
            e.printStackTrace();
        }
    }


    }
    
  
}
