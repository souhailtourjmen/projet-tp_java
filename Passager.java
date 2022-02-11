package src;

public class Passager extends Individu {

    private String passport ;
    public  Reservation rs;
    //setter 
    public void setpassport(String passport){
        this.passport=passport;
    }
    //getter 
    public String getpassport(){
        return this.passport ;
        
    } public Passager( String Nom, String prenom, String Adres, String Tel, String Email,String passport ) {
        super(Nom, prenom, Adres, Tel, Email);
        this.setpassport(passport);
       
    }
    public Passager( String Nom, String prenom, String Adres, String Tel, String Email,String passport ,Reservation rs) {
        super(Nom, prenom, Adres, Tel, Email);
        this.setpassport(passport);
        this.rs=rs;
    }
    
}
