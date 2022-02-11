package src;

import java.util.ArrayList;

public class Ville {
    private int N_ville ;
    private String Nom_ville ;
    private ArrayList<Aeropot>aeropots=new ArrayList<>();
    //setter
        public void setN_ville(int n_ville){
            this.N_ville=n_ville;
        } 
        public void setNom_ville(String nom_ville){
            this.Nom_ville=nom_ville;
        }
        public void setlist_aeros(ArrayList<Aeropot> aeropot){
            for(Aeropot list :aeropot)
                this.aeropots.add(list);
        }
    //getter
    public int getN_ville(){
        return this.N_ville;
    } 
    public String getNom_ville(){
        return this.Nom_ville;
    }
    public ArrayList<Aeropot>getlist_aeros(){
        
        return  this.aeropots;
    }
    //constructeur il n'y a aeroport 
    public Ville(int n_ville,String nom_ville) {
        this.setN_ville(n_ville);
        this.setNom_ville(nom_ville);
        
    }
    //constructeur il ya aeroport 
    public Ville(int n_ville,String nom_ville,ArrayList<Aeropot> aeropot) {
        this.setN_ville(n_ville);
        this.setNom_ville(nom_ville);
        this.setlist_aeros(aeropot);
    }
}
