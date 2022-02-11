// package src;

// import java.sql.Date;
// import java.util.ArrayList;

// public class Vol {
//     private int N_vol ;
//     private  Date date_dep ,date_arr ;
//     ArrayList<Reservation>reservations=new ArrayList<>();
//     //setter
//     public void setN_vol( int N_vol){
//         this.N_vol=N_vol;
//     }
//     public void setDate_dp(Date dt_dp){
//         this.date_dep=dt_dp;

//     }
//     public void setDate_ar(Date dt_ar){
//         this.date_arr=dt_ar;

//     }
//     public void setres(ArrayList<Reservation>res){
//         for(Reservation re :res)
//         this.reservations.add(re);
//     }
//     //getter
//     public int getN_vol( ){
//         return this.N_vol;
//     }
//     public Date getDate_dp(){
//             return this.date_dep ;

//     }
//     public Date getDate_ar(){
//         return this.date_arr;

//     }
//     //constructeur 
//     public Vol (int N_vol ,Date date_dep, Date date_arr ,ArrayList<Reservation>res){
//         this.setN_vol(N_vol);
//         this.setDate_dp(date_dep);
//         this.setDate_ar(date_arr);
//         this.setres(res);
//     }

// }



//----------------------------------------------------------------
// public ArrayList<VolGen> getvolGen(String request) throws ClassNotFoundException {
//     ArrayList<VolGen>vols= new ArrayList();
//     VolGen vol = new  VolGen();
//     try {
//         Connection cnx=cnnbd();
//         Statement statement =cnx.createStatement();
//         ResultSet rs = statement.executeQuery(request);
//         while (rs.next()) {
//             vol = new  VolGen();
//             vol.setN_vog(rs.getInt("NUMVOL"));
//             vol.setjourdep(rs.getDate("DATEDEP"));
//             vol.setjourarr(rs.getDate("DATERET"));
//             vol.sethdep(rs.getTimestamp("HEURDEP"));
//             vol.setharr(rs.getTimestamp("HEURARR"));
//             vol.setdepart(getaeroport(rs.getInt("AERODEP")));
//             vol.setarrive(getaeroport(rs.getInt("AEROARR")));
//             vol.setcomp(getcompagnie(rs.getInt("NUMCOMP")));
//             //System.out.println("\n"+vol.getArrivée().getVille().getNom_ville());
//             vols.add(vol);
        
//           // System.out.println("from\t"+rs.getInt("NUMVOL")+"\t to\t"+rs.getDate("DATEDEP")+"\t date depart\t"+rs.getDate("DATERET")+"\t time depart\t"+rs.getTimestamp("HEURDEP"));
//         }
//         cnx.close();
//         statement.close();
//     } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//         System.out.println("erur1");
//     }
    
// // System.out.println("from\t"+vildep+"\t to\t"+vilarr+"\t date depart\t"+datedp+"\t date return\t"+datear);
// return vols;
// }