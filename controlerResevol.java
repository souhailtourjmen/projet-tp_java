package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.ActionEvent;

public class controlerResevol implements Initializable {

    
    @FXML
    private Label ALLE;

    @FXML
    private Label DTALLER;

    @FXML
    private Label DTRET;

    @FXML
    private Label FROM;

    @FXML
    private Label HALLER;

    @FXML
    private Label HRET;

    @FXML private Label prix;

    @FXML
    private Label RETOUR;

    @FXML
    private Label TO;

    @FXML
    private Button VALIDE;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrol;

    @FXML
    private ImageView home;

    @FXML
    private ImageView exit;

    @FXML
    static  ObservableList<VolGen> data = FXCollections.observableArrayList();

    //LES VOL DE RSERVATION 
    static VolGen volaller ,volretour ; 
    // le prix 
    
    String pass="";
    @FXML
    void valide(ActionEvent event) {
        if((volaller!=null)&(ControllerPassager.i==0)){
                grid.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Passager.fxml"));
                AnchorPane anchorPane;
                try {
                    anchorPane = fxmlLoader.load();
                    grid.add(anchorPane, 0, 0); //(child,column,row)
            
                } catch (IOException e) {
                    // TODO Auto-generated catch block
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
                VALIDE.setText("Reserve");
            }else if((ControllerPassager.i!=0 )&(VALIDE.getText().equals("Reserve"))){
                grid.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("affichage.fxml"));
                AnchorPane anchorPane;
                try {
                    anchorPane = fxmlLoader.load();
                    grid.add(anchorPane, 0, 0); //(child,column,row)
            
                } catch (IOException e) {
                    // TODO Auto-generated catch block
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
                VALIDE.setText("Done");
                ControllerPassager.i=55;
                
            }else if ((ControllerPassager.i==55) & (VALIDE.getText().equals("Done"))){
                
                BDconection cont = new BDconection();
                LocalDate myDateObj = LocalDate.now();  
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = myDateObj.format(myFormatObj);
                String req="select PASSPORT from PASSAGERS where PASS='"+ControllerPassager.passager.getpassport()+"'";
                
                try (////////////////////////////////
                                    Connection cnx = cont.cnnbd()) {
                                        Statement st = cnx.createStatement();
                                        ResultSet rs =cont.select(req, st);
                                        while (rs.next()) {
                                            pass =rs.getString("PASSPORT");
                                        }
                                        cnx.close();
                                        st.close();
                                        rs.close();

                                    } catch (ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                if(volretour!=null){
                    req="insert into RESERVATION  values ('"+pass+"','"+formattedDate+"',"+volaller.getN_vog()+","+volretour.getN_vog()+",'"+pass+"')";
                } 
                else{
                    req="insert into RESERVATION (NUMREV,DATEREV,NUMVOLALLER,CODEPASS) values ('"+pass+"','"+formattedDate+"',"+volaller.getN_vog()+",'"+pass+"')";
                }
                try {
                    int res =cont.insert(req);
                    if(res==0){
                        App.showAlerterreur("insertion reservation failed");
                    }
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    App.showAlerterreur("insertion reservation failed");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    App.showAlerterreur("insertion reservation failed");
                }
                //send email
                Sendmail.send(ControllerPassager.passager.getEmail(),msgEmail()); 
                
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

    
    
        
    
   
     
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        remplirdt();
        setscroll();
        menu();
        
    }
   
    

    int nb=0;
    public void setscroll(){
        
        int row = 0;
        try {
            for(VolGen i :data) {
                ControllerItems.volGen=i;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ITEAM.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                grid.add(anchorPane, 1, row); //(child,column,row)
            
                
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                // setanchorpane
                FileInputStream input = new FileInputStream("src/img/Fichier 1.png");
                    Image image = new Image(input);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(223);
                    imageView.setFitHeight(200);
                //button economy
                Button btn = new Button();
                btn.setText("economy");
                Label lbl = new Label();
                lbl.setPadding(new Insets(1, 1, 1, 2));
                lbl.setText(String.valueOf(i.getPrice()));
                HBox root = new HBox(lbl,btn);
                root.setSpacing(10);
                root.setPadding(new Insets(15,20, 10,10));
                
                // button busini
                Button btn1 = new Button();
                btn1.setText("business class");
                Label lbl1 = new Label();
                lbl1.setPadding(new Insets(1, 1, 1, 2));
                lbl1.setText(String.valueOf(Math.round(i.getPrice()+i.getPrice()*0.3)));
                HBox root1 = new HBox(lbl1,btn1);
                root1.setSpacing(10);
                root1.setPadding(new Insets(15,20, 10,10));
                //set hbox in vbox
                VBox vbox = new VBox(root,root1);
                vbox.setLayoutX(50);
                vbox.setLayoutY(60);
                //settanchor
                AnchorPane anchor= new AnchorPane();
                anchor.getChildren().addAll(imageView,vbox);
                grid.add(anchor, 0, row);
                //event btn
                btn.setOnMouseClicked(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {

                        if((volaller==null )&(nb==0)) {
                        volaller=i;
                        volaller.setPrice(Integer.parseInt(lbl.getText()));
                        DTALLER.setText(String.valueOf(volaller.getjourdep()));
                        HALLER.setText(volaller.gethdep());
                        prix.setText(String.valueOf(volaller.getPrice()));
                        nb=1;
                        }
                        else if((volretour==null )&nb==1){
                            volretour=i;
                            volretour.setPrice(Integer.parseInt(lbl.getText()));
                            DTRET.setText(String.valueOf(volretour.getjourdep()));
                            HRET.setText(volretour.gethdep());
                            prix.setText(String.valueOf((Integer.valueOf(prix.getText()))+ volretour.getPrice()));

                         }
                         else{
                             nb=0;
                             volaller=null;
                             volretour=null;
                             prix.setText("0.0");
                             DTALLER.setText("date aller");
                             HALLER.setText("heure aller");
                             DTRET.setText("date Retour");
                             HRET.setText("heure Retour");
                         }
                       

                    }
                });
                //event btn1
                btn1.setOnMouseClicked(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {

                        if((volaller==null )&(nb==0)) {
                        volaller=i;
                        volaller.setPrice(Integer.parseInt(lbl1.getText()));
                        DTALLER.setText(String.valueOf(volaller.getjourdep()));
                        HALLER.setText(volaller.gethdep());
                        prix.setText(String.valueOf(volaller.getPrice()));
                        nb=1;
                        }
                        else if((volretour==null )&nb==1){
                            volretour=i;
                            volretour.setPrice(Integer.parseInt(lbl1.getText()));
                            DTRET.setText(String.valueOf(volretour.getjourdep()));
                            HRET.setText(volretour.gethdep());
                            prix.setText(String.valueOf((Integer.valueOf(prix.getText()))+ volretour.getPrice()));

                         }
                         else{
                             nb=0;
                             volaller=null;
                             volretour=null;
                             prix.setText("0.0");
                             DTALLER.setText("date aller");
                             HALLER.setText("heure aller");
                             DTRET.setText("date Retour");
                             HRET.setText("heure Retour");
                         }
                       

                    }
                });
                row++;
            }

           } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
   
    void remplirdt(){
        int i=0;
        for(VolGen v : data){
            if(i==0){
            FROM.setText(v.getdepart().getVille().getNom_ville());
            TO.setText(v.getArrivée().getVille().getNom_ville());
            i++;
        }
        }
       
            

    }
void menu(){
    exit.setOnMouseClicked(event -> {
        System.exit(0);
    });
    home.setOnMouseClicked(event -> {
        
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
    });
}

  //Message Email
  public String msgEmail() {
    String content="";
    try {
        content = Jsoup.parse(new File("src/mail/Mail1.html"), "UTF-8").outerHtml();
       // System.out.println(content);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    // Document document = Jsoup.parse(content);
    // Element div = document.getElementById("NOM");     
    // div.text("This is a sample content.");
    
    content.replaceAll("NOM","Souhail");
    content.replaceAll("PRENOM",ControllerPassager.passager.getPrenom());
    content.replace("PASSPORT",ControllerPassager.passager.getpassport());
    content.replace("CODERES",pass);
    content.replace("FROM",volaller.getdepart().getVille().getNom_ville());
    content.replace("TO12",volaller.getArrivée().getVille().getNom_ville());
    content.replace("DUREE",volaller.Calculer_durée());
    content.replace("AEROfr1m",volaller.getdepart().getNom_aeroport());
    content.replace("Aero13to",volaller.getArrivée().getNom_aeroport());
    content.replace("TITM",volaller.gethdep());
    content.replace("DATE",String.valueOf(volaller.getjourdep()));
    content.replace("PRICE",String.valueOf(volaller.getPrice()));
    content.replace("TOTAL",String.valueOf(volaller.getPrice()+50));



    return content;

  }  

 
   
    
      
}

