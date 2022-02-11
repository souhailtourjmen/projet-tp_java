package src;

public class Client extends Individu  {
    private int Cin ;
    private String password ;
    public Reservation rs;
    //setter 
    public void setCin(int Cin){
        this.Cin=Cin;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    //getter 
   
    public int getCin(){
        return this.Cin ;
    }
    public String getPassword() {
        return password;
    }
    //constructer avec reservation  ----------------------------------------------------------------
    public Client(int Cin, String Nom, String prenom,String Email, Reservation rs) {
        super( Nom, prenom ,Email);
         this.setCin(Cin);
         this.rs=rs;
    }
    //constructer sans reservation  ----------------------------------------------------------------
    public Client(int Cin, String Nom, String prenom,String Email ,String password) {
        super( Nom, prenom ,Email);
         this.setCin(Cin);
         this.setPassword(password);
         
    }

    
    
    
}
