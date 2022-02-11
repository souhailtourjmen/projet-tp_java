package src;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControllerItems implements Initializable {

    @FXML
    private Label aeroarr;

    @FXML
    private Label aerodep;

    @FXML
    private Label comp;

    @FXML
    private Label escale;

    @FXML
    private Label harr;

    @FXML
    private Label hdep;
    @FXML
    private Label villarr;

    @FXML
    private Label villdep;
   
    
   
   static VolGen volGen;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        remplir();
        
    }
    void remplir() {
        
        villdep.setText(volGen.getdepart().getVille().getNom_ville());
        villarr.setText(volGen.getArrivée().getVille().getNom_ville());
        comp.setText(volGen.getcomp().getNom());
        aerodep.setText(volGen.getdepart().getNom_aeroport());
        aeroarr.setText(volGen.getArrivée().getNom_aeroport());
        escale.setText(volGen.getAlert()+"("+volGen.Calculer_durée()+")");
        hdep.setText(volGen.gethdep());
        harr.setText(volGen.getharr());
    }
    void intilavol(){
        if(controlerResevol.volaller==null) {
            
            controlerResevol.volaller=volGen;
            
            
        }
        else{
             controlerResevol.volretour=volGen;
         }
    }
  
}
