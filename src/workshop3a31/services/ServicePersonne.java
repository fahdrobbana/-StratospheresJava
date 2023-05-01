/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a31.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import workshop3a31.entities.Personne;
import workshop3a31.utils.MyDB;

/**
 *
 * @author Andrew
 */
public class ServicePersonne  implements IService<Personne>{
    
    Connection con ; 
    Statement ste;
     
    
    
    
    
    
    public ServicePersonne() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }

    @Override
    public void Ajouter(Personne t) {
        
        try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
            String req = "INSERT INTO `workshop`.`personne` (`nom`,`prenom`,`age`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()+"');";
            
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   /* @Override
    public void Ajouter2(Personne t) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO .`personne` (`nom`,`prenom`,`age`) VALUES (?,?,?);");
            
            pre.setString(1, t.getNom());
            pre.setString(2, t.getPrenom());
            pre.setInt(3, t.getAge());
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/
    @Override
    public void Modifier(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Supprimer(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Personne> Afficher() {
        ArrayList<Personne> pers = new ArrayList<>();
        try {
            ste =con.createStatement();
            String req = "SELECT * FROM `personne`";
            ResultSet res =ste.executeQuery(req);
            
            while(res.next()){
                int id = res.getInt("id");
                String nom = res.getString(2);
                String prenom =res.getString("prenom");
                int age = res.getInt(4);
                
                Personne p = new Personne(id, nom, prenom, age);
                pers.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  pers;
       
    }

    @Override
    public ObservableList<Personne> readAnnonce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Personne> readcomm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
