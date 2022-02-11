package src;

import java.util.ArrayList;

public class Compagnie {
    private int Code ;
    private  String Nom ;
    private ArrayList<VolGen>volgen=new ArrayList<>();
    //setter
    public void setCode(int cd){
        this.Code=cd;

    }
    public void setNom(String Nm){
        this.Nom=Nm;

    }
    public void setlivol_gen(ArrayList<VolGen>volGens){
        for(VolGen vlg:volGens)
            this.volgen.add(vlg);

    }
    
    //getter
    public int getCode(){
        return this.Code;
    }
    public String getNom(){
        return this.Nom;
    }
    public ArrayList<VolGen> getlivolg(){
        return this.volgen ;
    }
    //constructeur
    public Compagnie (int cd ,String Nm ,ArrayList<VolGen> volGens){
        this.setCode(cd);
        this.setNom(Nm);
        this.setlivol_gen(volGens);
    }
    public Compagnie(int int1, String Nm) {
        this.setCode(int1);
        this.setNom(Nm);
    }

    
}
