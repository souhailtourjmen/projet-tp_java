package src;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class controllerTicket implements Initializable {

    @FXML
    private Label comp;

    @FXML
    private Label date;

    @FXML
    private ImageView exit;

    @FXML
    private Label from;

    @FXML
    private Label from2;

    @FXML
    private ImageView home;

    @FXML
    private Label numpass;

    @FXML
    private Label passName;

    @FXML
    private Label time;

    @FXML
    private Label to;

    @FXML
    private Label to2;

    @FXML
    void evexit(MouseEvent event) {

    }

    @FXML
    void home(MouseEvent event) {

    }
    static Passager passager ;
    static VolGen passVol ;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        numpass.setText(passager.getpassport());
        passName.setText(passager.getNom()+" "+passager.getPrenom());
        from.setText(passVol.getdepart().getVille().getNom_ville());
        to.setText(passVol.getArrivée().getVille().getNom_ville());
        time.setText(passVol.gethdep());
        date.setText(String.valueOf(passVol.getjourdep()));
        from2.setText(passVol.getdepart().getNom_aeroport());
        to2.setText(passVol.getArrivée().getNom_aeroport());
        comp.setText(passVol.getcomp().getNom());

    }

}
