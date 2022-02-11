package src;

import java.sql.Date;
import java.sql.Time;


public class Reservation {
    private long Num_res;
    private Date date ;
    private Time timevol ;
    private VolGen vol ;
    private Client  client ;
    private Passager passager ;
   
    //setter
    public void setnumres(long num_res){
        this.Num_res=num_res;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTimevol(Time timevol) {
        this.timevol = timevol;
    }
    public void setclient(Client client){
            this.client=client;
    }
    
    public void setpassager(Passager passager){
       
            this.passager=passager;
    }
    public void setvol(VolGen vol){
        this.vol=vol ;
    }
    //getter 
    public long getnumres(){
        return this.Num_res;
        
    }
    public Date getDate() {
        return date;
    }
    public Time getTimevol() {
        return timevol;
    }
    public Client getClients(){
        return this.client;
    }
    public Passager getPassagers(){
        return this.passager;
    }
    public VolGen getvol(){
        return this.vol ;
    }
    //constructeur pour le cas il ya client 
    public Reservation (long num_res ,Date date,Client client  ,VolGen vol ){
        this.setnumres(num_res);
        this.setDate(date);
        this.setvol(vol);
    }
    //constructeur  passager 
    public Reservation (long num_res ,Date date ,Passager passager ,VolGen vol ){
        this.setnumres(num_res);
        this.setDate(date);
        this.setTimevol(timevol);
        this.setpassager(passager);
        this.setvol(vol);
    }

    
}
