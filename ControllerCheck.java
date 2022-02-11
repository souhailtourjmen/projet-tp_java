package src;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControllerCheck implements Initializable {

    @FXML
    private GridPane GRID;

    @FXML
    private TextField PASS;

    @FXML
    private ScrollPane SCROL;

    @FXML
    private Button SEARCH;

    @FXML
    void Search(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String req="select * from RESERVATION WHERE CODEPASS ="+checkpass(PASS.getText());
        BDconection conn = new BDconection();
        Connection cnx=conn.cnnbd();
        Statement st = cnx.createStatement();
        ResultSet rs =conn.select(req, st);
        while(rs.next()){
            String req1="select * from VOL WHERE NUMVOL ="+rs.getInt("NUMVOLALLER");
            setData(conn,cnx,req1,rs.getString("NUMREV"));
            if(rs.getInt("NUMVOLRETOUR")!=0){
            req1="select * from VOL WHERE NUMVOL ="+rs.getInt("NUMVOLRETOUR"); 
            setData(conn,cnx,req1,rs.getString("NUMREV"));
            }
        }
        cnx.close();
        st.close();
        rs.close();
        setscroll();

    }
    public String checkpass(String pass) throws ClassNotFoundException, SQLException{
        String req="select PASSPORT from PASSAGERS WHERE PASS ='"+pass+"'";
        BDconection conn = new BDconection();
        Connection cnx=conn.cnnbd();
        Statement st = cnx.createStatement();
        ResultSet rs =conn.select(req, st);
        String Pass="";
        while(rs.next()){
           
        Pass= rs.getString("PASSPORT");
            
        }
        
        cnx.close();
        st.close();
        rs.close();
        if(pass.equals("")){
            App.showAlerterreur("Passport Invalid");
        }
        return Pass;
        
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      SCROL.setVisible(false);
        
    }
    @FXML
    public  ObservableList<VolGen> data = FXCollections.observableArrayList();
    //////////////set data//////////////////
     //set aeroport
     public Aeropot  getaeroport(BDconection conn,Connection cnx,int id) throws SQLException, ClassNotFoundException{
        Statement st1 = cnx.createStatement();
        ResultSet rs1 =conn.select("select * from AEROPORT where NUMERO ="+id, st1);
        Aeropot aero=null;
        while (rs1.next()){
           Statement st2 = cnx.createStatement();
           ResultSet rs2 =conn.select("select * from VILLE where CODEV ="+rs1.getInt("CODEV"),st2);
           Ville ville = null;
           while (rs2.next()){
                   ville= new Ville(rs2.getInt("CODEV"),rs2.getString("LIBELLE"));
           }
           aero= new Aeropot(rs1.getInt("NUMERO"),rs1.getString("NOMEROP"),ville);
           st2.close();
           rs2.close();
        }

        st1.close();
        rs1.close();
        return aero;

    }
    //setter compa
    public  Compagnie  getcompagnie(BDconection conn,Connection cnx,int id) throws SQLException, ClassNotFoundException{
    Compagnie compagnie =null;
    Statement st1 = cnx.createStatement();
    ResultSet rs =conn.select("select * from COMPAGNIE where ID_COMP ="+id, st1);
    try {
        while (rs.next()){
            compagnie = new Compagnie(rs.getInt("ID_COMP"),rs.getString("NOMCOMP"));
            //System.out.println("numaro\t"+rs.getInt("ID_COMP")+"\t"+rs.getString("NOMCOMP")+"\n");
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.out.println("problem remplir compagnier");
    }
    st1.close();
    rs.close();
    return compagnie;

}
    
    //setter methods
    @FXML
    public  void setData(BDconection conn,Connection cnx,String req,String string) throws ClassNotFoundException, SQLException{
        
        
         try {
            Statement st = cnx.createStatement();
             ResultSet rs =conn.select(req, st);
             while (rs.next()) {
                VolGen vol=new VolGen();
                vol.setN_vog(rs.getInt("NUMVOL"));
                vol.setjourdep(rs.getDate("DATEDEP"));
                vol.setjourarr(rs.getDate("DATERET"));
                vol.sethdep(rs.getTimestamp("HEURDEP"));
                vol.setharr(rs.getTimestamp("HEURARR"));
                vol.setdepart(getaeroport(conn,cnx,rs.getInt("AERODEP")));
                vol.setarrive(getaeroport(conn,cnx,rs.getInt("AEROARR")));
                vol.setcomp( getcompagnie(conn,cnx,rs.getInt("NUMCOMP")));
                vol.setPrice(rs.getInt("PRICE"));
                vol.setCodereservation(string);
                data.add(vol);
             }
             
             st.close();
             rs.close();
         } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        
         
        

    
}
    
public void setscroll() throws IOException{
    int tik=1 ,tr=0; 
    int row = 1;
    SCROL.setVisible(true);
        for(VolGen i :data) {
            ControllerItems.volGen=i;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ITEAM.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            GRID.add(anchorPane, 1, row); //(child,column,row)
            //set grid width
            GRID.setMinWidth(Region.USE_COMPUTED_SIZE);
            GRID.setPrefWidth(Region.USE_COMPUTED_SIZE);
            GRID.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            GRID.setMinHeight(Region.USE_COMPUTED_SIZE);
            GRID.setPrefHeight(Region.USE_COMPUTED_SIZE);
            GRID.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
            if(tik==2){
             // button busini
             Button btn = new Button();
             btn.setText("Delete");
             Button btn1 = new Button();
             btn1.setText("Edit");
             VBox root = new VBox(btn,btn1);
             root.setSpacing(10);
             root.setPadding(new Insets(15,20, 10,10));
             root.setLayoutX(50);
             root.setLayoutY(60);
             //settanchor
             AnchorPane anchor= new AnchorPane();
             anchor.getChildren().addAll(root);
             GRID.add(anchor, 0, row);
             //event btn
             if(tr==0){
             btn.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    String req="delete from RESERVATION where NUMREV ='"+i.getCodereservation()+"'";
                    BDconection bd=new BDconection();
                    try {
                        if(bd.insert(req)==0){
                            App.showAlerterreur("delete reservation failed");
                            
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                   
                    anchorPane.getChildren().clear(); 
                    controlerhome.stage.close();
                }
            });
            tr=1;
        }
            //event btn1
            btn1.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    

                   
                }
            });
            tik=0;
        }
             tik++;
            row=row+2;
        
    }
    }
}
