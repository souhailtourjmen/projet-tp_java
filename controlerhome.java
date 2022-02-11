package src;




import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;



import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class controlerhome  implements Initializable {

    @FXML
    private Button Search;

    @FXML
    private Label compte;

    @FXML
    private  DatePicker depart;

    @FXML
    private ComboBox destination;

    @FXML
    private ComboBox from;

    @FXML
    private DatePicker retour;

    @FXML
    private Button home;

    @FXML
    private Button editflight;

    @FXML
    private Button checkflight;

    @FXML
    private CheckBox al;

    @FXML
    private CheckBox ret;
    @FXML
    private GridPane grid; 
    @FXML
    private ScrollPane scrol;

    @FXML
    private ImageView menu;

    @FXML
    private ImageView menuback;

    @FXML
    private AnchorPane slider;

    @FXML
    private ImageView Exit;

    
   
    boolean eta=false;
       
    @FXML
    public  ObservableList<VolGen> data = FXCollections.observableArrayList();
    
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
    public  void setData(String req) throws ClassNotFoundException, SQLException{
        
        BDconection conn = new BDconection();
         try {
             Connection cnx=conn.cnnbd();
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
                data.add(vol);
             }
             cnx.close();
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
    boolean check = true;
     @FXML
    public  void  getRet() throws ClassNotFoundException {
        data.clear();
        BDconection conn= new BDconection();
        //datelocal
        LocalDate myDateObj = LocalDate.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        LocalDate datedepart=depart.getValue();
        String  datedp= datedepart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy "));
        if(myDateObj.compareTo(datedepart) < 0){
        LocalDate datearrive=retour.getValue();
        String  datear= datearrive.format(DateTimeFormatter.ofPattern("dd-MM-yyyy "));
        String vildep =from.getSelectionModel().getSelectedItem().toString();
        String vilarr =destination.getSelectionModel().getSelectedItem().toString();
        String req1="select * from vol ,DELAIS_VOL d where  aerodep in (select numero from aeroport a , ville v  where a.codev=v.codev and v.libelle='"+vildep+"' )and aeroarr in (select numero from aeroport a , ville v where a.codev=v.codev and v.libelle='"+vilarr+"' )and escale is NULL and datedep='"+datedp+"'";
        String req2="select * from vol ,DELAIS_VOL d where aerodep in (select numero from aeroport a , ville v where a.codev=v.codev and v.libelle='"+vilarr+"' )and aeroarr in (select numero from aeroport a , ville v where a.codev=v.codev and v.libelle='"+vildep+"' )and escale is NULL and datedep>='"+datear+"'";
        try {
            setData(req1);
            setData(req2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }else{App.showAlerterreur("date failed");
    check=false;
}



    }

    @FXML
    void Searchvol(ActionEvent event) {
        // scrol.setVisible(true);
        // setscroll();
            try {
                getRet();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            controlerResevol.data=data;
        if(check==true){
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resvol.fxml"));
            Scene scene= new Scene(fxmlLoader.load());
           // StageApp.stage=new Stage();
            Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
           App.stage.getIcons().add(icon);
           App.stage.setTitle("RESERVATION !");
           App.stage.setScene(scene);
           App.stage.show();
            
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("errer RESERVATION ");
            e.printStackTrace();
        }
    }else{
        showHome();
    }
    }
    static Stage stage=new Stage();
    int x=0;
    @FXML
    void eventHome(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        Scene scene= new Scene(root);
        Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
        stage.getIcons().add(icon);
        stage.setTitle("EDIT_PROFIL!");
        stage.setScene(scene);
        stage.show();
        x=1;
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }

    @FXML
    void eventcheckfli(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Checkflight.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        Scene scene= new Scene(root);
        Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
        stage.getIcons().add(icon);
        stage.setTitle("CHECK_FLIGHT!");
        stage.setScene(scene);
        stage.show();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}

    @FXML
    void eventeditfli(ActionEvent event) {

    }
    static String account ;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

           // scrol.setVisible(false);
           setscroll();
        setmenu();
        
        ObservableList data1 = FXCollections.observableArrayList();
        BDconection conn = new BDconection();
        try {
            data1 =conn.getVille();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        from.setItems(data1);
        destination.setItems(data1);
        
       
        compte.setText(account);
        if(x==1){
            stage.hide();
        }
        
        
        

    }
    //--------------------------------------------------------------------------------------------------------------------

    
    void setmenu(){
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(-176);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(false);
                menuback.setVisible(true);
            });
        });

        menuback.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(true);
                menuback.setVisible(false);
            });
        });
    }
    
    public void setscroll(){
        scrol.setStyle("-fx-background-image: url('pexels-jeshootscom-1201995.jpg');");
        int column = 0;
        int row = 1;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("pageweb.fxml"));
        AnchorPane anchorPane;
        try {
            anchorPane = fxmlLoader.load();
            grid.add(anchorPane, 0, 1); //(child,column,row)
             //set grid width
            //  grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            //  grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            //  grid.setMaxWidth(Region.USE_PREF_SIZE);

            //  //set grid height
            //  grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            //  grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            //  grid.setMaxHeight(Region.USE_PREF_SIZE);

             GridPane.setMargin(anchorPane, new Insets(10));
    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //---------------------------SHOW HOME-----------------------------------
    public void showHome(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        Scene scene= new Scene(root);
       // Stage stage=new Stage();
        Image icon =new Image(getClass().getResourceAsStream("/img/logocompany.png"));
        App.stage.getIcons().add(icon);
        App.stage.setTitle("home!");
        App.stage.setScene(scene);
        App.stage.show();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
 
  
}
