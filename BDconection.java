package src;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class BDconection {



    private   String url="jdbc:oracle:thin:@localhost:1521:xe"  ;
    private   String user="tunisiar" ;
    private   String mdp ="123" ;
    
    public BDconection(  ) {
        

    };

    //methode connection
    public  Connection cnnbd() throws ClassNotFoundException{
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, user, mdp);
            System.out.println("connection valide");
            return con;
            
            


    }
     catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
}
//methode select
public  ResultSet select( String request ,Statement statement ) throws ClassNotFoundException, SQLException {
    ResultSet rs = null;
   return  rs = statement.executeQuery(request);
    
    
}
// methode insert 
public int insert(String req  ) throws SQLException, ClassNotFoundException{
    int rs=0;
    try {
        Connection cnx=cnnbd();
        Statement statement =cnx.createStatement();
        rs = statement.executeUpdate(req);
        
        cnx.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rs ;
     
    }



//verification compte client
public  int checkaccount(String login , String pwd ) throws ClassNotFoundException {
           
        
     int rsn =0;
    try {
        Connection cnx=cnnbd();
        Statement statement =cnx.createStatement();
        ResultSet rs = statement.executeQuery("select email,password from client  where email='"+login+"' and password='"+pwd+"'");
        while (rs.next()) {
            // String email = rs.getString("EMAIL");
            // String password = rs.getString("PASSWORD");
           rsn++;
          
        }
        cnx.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rsn;
}

    //return ville
    public  ObservableList getVille() throws ClassNotFoundException {
        ObservableList data = FXCollections.observableArrayList();
    try {
        Connection cnx=cnnbd();
        Statement statement =cnx.createStatement();
        ResultSet rs = statement.executeQuery("select LIBELLE from Ville order by LIBELLE ");
        while (rs.next()) {
           data.add(new String(rs.getString("LIBELLE")));
            
          
        }
        cnx.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return data ;
}


}






  
    



