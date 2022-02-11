package src;

public class Individu {
   
    private  String Nom ;
    private  String Prenom ;
    private  String Adresse ;
    private  String tel ;
    private  String Email ;

    //setter 
    
    public void setNom(String Nom){
        this.Nom=Nom;
    }
    public void setPrenom(String prenom){
        this.Prenom=prenom;
    }
    public void setAdresse(String adrs){
        this.Adresse=adrs;
    }
    public void settel(String tel){
        this.tel=tel;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
    //getter 
    
    public String getNom(){
        return this.Nom;
    }
    public String getPrenom(){
        return this.Prenom;
    }
    public String getAdresse(){
        return this.Adresse;
    }
    public String gettel(){
        return this.tel;
    }
    public String getEmail(){
        return this.Email;
    }
    //constructer passager
    public Individu (String Nom,String prenom,String Adres ,String tel ,String Email){
        this.setNom(Nom);
        this.setPrenom(prenom);
        this.settel(tel);
        this.setEmail(Email);
    }
    //constructer client
    public Individu (String Nom,String prenom ,String Email){
        this.setNom(Nom);
        this.setPrenom(prenom);
        this.setEmail(Email);
        
    }
}
