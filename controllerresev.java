package src;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class controllerresev implements Initializable {

    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        controllerTicket.passager=ControllerPassager.passager;
        int row =0;
        setGrid(controlerResevol.volaller,1);
        if(controlerResevol.volretour!=null) {
        row++;
        setGrid(controlerResevol.volretour,3);
    }
}
void setGrid(VolGen vol ,int row) {
    FXMLLoader fxmlLoader = new FXMLLoader();
        controllerTicket.passVol=vol;
        fxmlLoader.setLocation(getClass().getResource("ticket.fxml"));
        AnchorPane anchorPane;
        try {
            anchorPane = fxmlLoader.load();
            grid.add(anchorPane, 0, row); //(child,column,row)
    
        } catch (IOException e) {
            
            e.printStackTrace();
        } 
        //set grid width
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
    }
    
     

}

