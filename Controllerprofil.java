package src;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controllerprofil implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField Nom;

    @FXML
    private Button edit;

    @FXML
    private Label login;

    @FXML
    private TextField prenom;

    @FXML
    void edit(ActionEvent event) {
        
        edit.setText("Save");
        edit.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                BDconection bd=new BDconection();
                controlerhome ct=new controlerhome();
                String req="UPDATE CLIENT SET NOM = '"+Nom.getText()+"', PRENOM= '"+prenom.getText()+"', CIN = '"+Integer.parseInt(Cin.getText())+"' WHERE EMAIL='"+login.getText()+"'";
                 try {
                    int i =bd.insert(req);
                    if (i ==1){
                        App.showAlerterreur("Modify");
                    }else{
                        App.showAlerterreur("No modify");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                ct.showHome();
                controlerhome.stage.close();
            }



            });}

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SetClient();   
    }
     public void SetClient() {
        
        BDconection bd=new BDconection();
        String req="select * from client where EMAIL ='"+controlerhome.account+"'";
        
            try (Connection cnx = bd.cnnbd()) {
                Statement st = cnx.createStatement();
                ResultSet rs =bd.select(req, st);
            
                while (rs.next()) {
                    login.setText(rs.getString("EMAIL"));
                    Nom.setText(rs.getString("NOM"));
                    prenom.setText(rs.getString("PRENOM"));
                    Cin.setText(String.valueOf(rs.getInt("CIN")));
                   
                }
               

            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        
        
    }

}
