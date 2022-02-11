package src;

import java.sql.Date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class VolGen {

    private int n_vol ;
    private Date jourdep ;
    private Date jourarr ;
    private String hdep ;
    private String harr ;
    private Aeropot depart ;
    private Aeropot arrivée ;
    private ArrayList<Escale> escale =new ArrayList<>();
    private Compagnie compagnie ;
    private int price ;
    private String codereservation ;
    private String alert ="vol sans escale"; //message escale ou non  
    String result ="";
   private  int ret,retSeconds,retMinutes,retHours =0;

   
    

   
    //setter 
  
        public void setN_vog(int n_vol){
                this.n_vol=n_vol;
        }
       
        public void setCodereservation(String codereservation) {
            this.codereservation = codereservation;
        }
        public void setjourdep(Date jour){
            this.jourdep=jour;
        }
        public void setjourarr(Date jour){
            this.jourarr=jour;
        }
        public void sethdep(Timestamp time){

            this.hdep = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
        }
        public void setharr(Timestamp time){
            this.harr=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
        }
        
        public void setcomp(Compagnie compagnie){
            this.compagnie =compagnie;
            
        }
        public void setdepart(Aeropot depart){
            this.depart=depart ;
        }   
        public void setarrive(Aeropot arrivée){
            this.arrivée=arrivée;
            
        }
        public void setlescales(Escale escales){
            
                for(Escale i:escale){
                    this.escale.add(i);
                } 
                this.setAlert("vol avec Escale");
        }
        public void setAlert(String alert) {
            this.alert = alert;
        }
        
        public void setPrice(int price) {
            this.price = price;
        }
    //getter 
        public int getN_vog(){
            return this.n_vol;
        }
        public Date getjourdep(){
            return  this.jourdep;
        }
        public Date  getJourarr() {
            return this.jourarr;
        }
        public String gethdep(){
             String ret=this.hdep.substring(11,13)+":"+this.hdep.substring(14,16)+":"+this.hdep.substring(17,19);
             System.out.println(ret);
             return ret;
        }
        public String getharr(){
            return  this.harr.substring(11,13)+":"+this.harr.substring(14,16)+":"+this.harr.substring(17,19);
        }
        public Compagnie getcomp(){
            return this.compagnie;
        }
        public Aeropot getdepart(){
            return this.depart;
        }
        public Aeropot getArrivée() {
            return arrivée;
        }
        public ArrayList<Escale> getlescales(){
            return  this.escale;
        }
        public String getAlert() {
            return alert;
        }
        public int getPrice() {
            return price;
        }
        public String getCodereservation() {
            return codereservation;
        }
        // constructeur 
        public VolGen(){

        }
    //constructeur 
     public VolGen (int n_vol ,Date jourdep ,Date jourarr,Timestamp hdep,Timestamp harr ,Compagnie compagnie ,Aeropot arrivée,Aeropot depart){
        this.setN_vog(n_vol);
        this.setjourdep(jourdep);
        this.setjourarr(jourarr);
        this.sethdep(hdep);
        this.setharr(harr);
        this.setcomp(compagnie);
        this.setarrive(arrivée);
        this.setdepart(depart);
        this.setAlert("vol sans escale");
     }
     //methode time
     public void gettime() {
         retHours=Integer.valueOf(this.harr.substring(11,13))-Integer.valueOf(this.hdep.substring(11,13));
         retMinutes=Integer.valueOf(this.harr.substring(14,16))-Integer.valueOf(this.hdep.substring(14,16));
         retSeconds=Integer.valueOf(this.harr.substring(17,19))-Integer.valueOf(this.hdep.substring(17,19));
         if(retSeconds>59){
                    retMinutes++;
                    retSeconds =0;
                                        }
        if(retMinutes>59){
                    retHours ++;
                    retMinutes=0;
                                 } 

     }

    //    // methode calaculer le durée entre l'heure de depart et l'heure d'arrivée
      public String Calculer_durée(){
           result ="";
                 if(this.getAlert().equals("vol sans escale")){
                     gettime();
                    result =retHours+"h"+retMinutes+"m"+retSeconds+"s";
                        }
                else{
                    //la somme de durre de tous les escales 
                     for(Escale i:escale){
                        i.gettime(String.valueOf(i.getHdep()));
                        if(retSeconds>59){
                            retMinutes++;
                            retSeconds =0;
                                                }
                            if(retMinutes>59){
                                 retHours ++;
                                 retMinutes=0;
                                         }
                     
                       
                     }
                    result =retHours+"h"+retMinutes+"min"+retSeconds+"s";  
                        }
                             
        return result;
    }
    
    // affichage pour test 
     
   
   public String tostring() {
    StringBuilder builder = new StringBuilder();
           builder.append( " n° vol \t" ).append( this.getN_vog())
           .append(" date depart: \t").append(this.getjourdep())
           .append(" date arriver: \t").append(this.getJourarr())
           .append(" heure depart: \t").append(this.gethdep())
           .append(" heure arriver: \t").append(this.getharr())
          .append( " aeropot depart: \t " ).append( this.getdepart().getNom_aeroport())
          .append( " aeropot arriver: \t " ).append( this.getArrivée().getNom_aeroport())
          .append(" nom compagnie: \t").append(this.getcomp().getNom())
           .append( "\n" );
    return builder.toString();
}
  

    
}
