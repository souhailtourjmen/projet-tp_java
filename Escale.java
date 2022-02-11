package src;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class Escale {
    private int  Nu_escale ;
    private Time H_dep ,H_arr;
    private Aeropot escale ;
    private VolGen  volgen;
    int retSeconds,retMinutes,retHours =0;
    //setter
        public void stN_escale(int N_esc){
            this.Nu_escale=N_esc;
        }
        public void setHdep(Time h_dep){
            this.H_dep=h_dep;
            this.gettime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(h_dep));
        }
        public void setHarr(Time h_arr){
            this.H_arr=h_arr;
            this.gettime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(h_arr));
        }
        public void setescale(Aeropot escale){
            this.escale=escale;
        }
        public void setvolgen(VolGen volgen){
            this.volgen=volgen;
        }
        public void setRetHours(int retHours) {
            this.retHours = retHours;
        }
        public void setRetMinutes(int retMinutes) {
            this.retMinutes = retMinutes;
        }
        public void setRetSeconds(int retSeconds) {
            this.retSeconds = retSeconds;
        }

    //getter
        public int gettN_escale(){
            return this.Nu_escale;
        }
        public Time getHdep(){
            return this.H_dep;
        }
        public Time getHarr(){
            return this.H_arr;
        }
        public Aeropot getescale(){
            return this.escale;
        }
        public VolGen getvolgen(){
            return this.volgen;
        }
        public int getRetHours() {
            return retHours;
        }
        public int getRetMinutes() {
            return retMinutes;
        }
        public int getRetSeconds() {
            return retSeconds;
        }

    //constructeur
    public Escale(int N_esc,Time h_dep,Time h_arr,Aeropot escale,VolGen volgen){
        this.stN_escale(N_esc);
        this.setHdep(h_dep);
        this.setHarr(h_arr);
        this.setescale(escale);
        this.setvolgen(volgen);
    }
     // methode calaculer le durée entre l'heure de depart et l'heure d'arrivée
     public void gettime(String time) {
        this.setRetHours(Integer.parseInt(time.substring(11,13)));
        this.setRetMinutes(Integer.parseInt(time.substring(14,16)));
        this.setRetSeconds(Integer.parseInt(time.substring(17,19)));
       

    }
}
