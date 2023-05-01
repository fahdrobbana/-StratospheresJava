/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.entity.Annonce;
import users.utils.MyConnectionn;

/**
 *
 * @author Amoulette
 */
public class ServiceAnnonce implements IService<Annonce> {

    Connection con;
    Statement ste;

    private PreparedStatement pre;
    private ResultSet rs;

    public ServiceAnnonce() {

        con = MyConnectionn.getInstance().getConn();

    }

    @Override
    public void Ajouter(Annonce t) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO .`annonce` (`nom`,`image`,`descreption`,`titre`,`tel`,`email`,`local`,`etat`,`categorie`) VALUES (?,?,?,?,?,?,?,?,?);");
            pre.setString(1, t.getNom());
            pre.setString(2, t.getImage());
            pre.setString(3, t.getDescreption());
            pre.setString(4, t.getTitre());
            pre.setInt(5, t.getTel());
            pre.setString(6, t.getEmail());
            pre.setString(7, t.getLocal());
            pre.setString(8, t.getEtat());
            pre.setString(9, t.getCategorie());
            pre.executeUpdate();
            System.out.println("Annonce ajoutée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Annonce t) {
        try {
            String requete = "UPDATE `annonce` SET"
                    + " `nom`=?,"
                    + " `image`=?,"
                    + " `descreption`=?,"
                    + " `titre`=?,"
                    + " `tel`=?,"
                    + " `email`=?,"
                    + " `local`=?,"
                    + " `etat`=?,"
                    + "`categorie`=? where `id`=?";
            PreparedStatement pre = con.prepareStatement(requete);
            // pre.setInt   (1, t.getId());
            pre.setString(1, t.getNom());
            pre.setString(2, t.getImage());
            pre.setString(3, t.getDescreption());
            pre.setString(4, t.getTitre());
            pre.setInt(5, t.getTel());
            pre.setString(6, t.getEmail());
            pre.setString(7, t.getLocal());
            pre.setString(8, t.getEtat());
            pre.setString(9, t.getCategorie());
            pre.setInt(10, t.getId());

            pre.executeUpdate();
            System.out.println("Annonce modifié avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<String> GetNamesAnnonce() {
        String req = "select titre from annonce";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = con.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("titre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public void Supprimer(Annonce t) {
       
        String req = "delete from annonce where id = ?";

        try {
            pre = con.prepareStatement(req);
            pre.setInt(1, t.getId());
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Annonce> readAnnonce() {
         String req = "SELECT * FROM `annonce`";
       ObservableList<Annonce> pers = FXCollections.observableArrayList();
        
        try {
            ste = con.createStatement();
           
            ResultSet res = ste.executeQuery(req);

            while (res.next()) {
                int id = res.getInt("id");
                String nom = res.getString(2);
                String image = res.getString(3);
                String descreption = res.getString(4);
                String titre = res.getString(5);
                int tel = res.getInt(6);
                String email = res.getString(7);
                String local = res.getString(8);
                String etat = res.getString(9);
                String categorie = res.getString(10);

                Annonce p = new Annonce(id, nom, image, descreption, titre, tel, email, local, etat, categorie);
                pers.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pers;
    }

    @Override
    public ArrayList<Annonce> Afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Annonce> readcomm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public int GetIdAnnone(String value) {
        String req = "select id from annonce where titre = '" + value + "';";

        int id = 0;
        try {
            ste = con.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
           
        }
        return id;
    }
public List<Annonce> listAnnonce() {
    List<Annonce> annonce = new ArrayList<>();
    try {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM annonce");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
           int id = res.getInt("id");
                String nom = res.getString(2);
                String image = res.getString(3);
                String descreption = res.getString(4);
                String titre = res.getString(5);
                int tel = res.getInt(6);
                String email = res.getString(7);
                String local = res.getString(8);
                String etat = res.getString(9);
                String categorie = res.getString(10);

           
            

             Annonce p = new Annonce(id, nom, image, descreption, titre, tel, email, local, etat, categorie);
            annonce.add(p); 
        }
        res.close();
        stmt.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return annonce;
}
}
    
