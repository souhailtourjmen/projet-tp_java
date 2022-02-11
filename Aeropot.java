package src;

import java.util.ArrayList;

public class Aeropot {
    private int No_aeroport ;
    private String Nom_aeroport ;
    private  Ville ville;
    private  ArrayList<VolGen>arrive=new ArrayList<>();
    private  ArrayList<VolGen>depart=new ArrayList<>();
    private  ArrayList<Escale>escale=new ArrayList<>();
    //setter 
    public void setN_aeroport(int N_aero){
        this.No_aeroport=N_aero;
    }
    public void setNom_aeroport(String Nom_aero){
        this.Nom_aeroport=Nom_aero;
    }
    public void setVille(Ville ville){
        this.ville=ville;
    }
    public void setlarrive(ArrayList<VolGen>arrives){
        for (VolGen larr :arrives)
            this.arrive.add(larr);
    }
    public void setldepart(ArrayList<VolGen>departs){
        for (VolGen ldp :departs)
            this.depart.add(ldp);
    }
    
    public void setlescales(ArrayList<Escale>escales){
        for (Escale lesc :escales)
            this.escale.add(lesc);
    }
    
    //getter
    public int getN_aeroport(){
        return this.No_aeroport;
    }
    public String getNom_aeroport(){
        return this.Nom_aeroport;
    }
    public Ville getVille(){
        return this.ville;
    }
    public ArrayList<VolGen> getlarrive(){
        return this.arrive;
    }
    public ArrayList<VolGen> getldepart(){
        return this.depart;
    }
    public ArrayList<Escale> getlescales(){
        return this.escale;
    }
    //constructeur
    public Aeropot(int N_aero , String Nom_aero,Ville ville ){
        this.setN_aeroport(N_aero);
        this.setNom_aeroport(Nom_aero);
        this.setVille(ville);
    }
     //constructeur le cas que il y a escale , arrive et depart
     public Aeropot(int N_aero , String Nom_aero,Ville ville,ArrayList<VolGen>arrives,ArrayList<VolGen>departs,ArrayList<Escale>escales ){
        this.setN_aeroport(N_aero);
        this.setNom_aeroport(Nom_aero);
        this.setVille(ville);
        this.setlarrive(arrives);
        this.setldepart(departs);
        this.setlescales(escales);
    }
    
}
