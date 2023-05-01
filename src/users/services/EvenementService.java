/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.services;

import users.entity.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.utils.MyConnectionn;

/**
 *
 * @author joujo
 */
public class EvenementService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;
    public Date date;

    public EvenementService() {
        conn = MyConnectionn.getInstance().getConn();

    }

    public Boolean ajouterEventPst(Evenement e) {
        String req = "insert into evenement (nom,lieu,date,description,datefin,nbr_personnes) values (?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getLieu());
            pst.setDate(3, e.getDate());
            pst.setString(4, e.getDescription());
            pst.setDate(5, e.getDatefin());
            pst.setInt(6, e.getNbr_personnes());

            pst.executeUpdate();
     return true;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
      public ObservableList<String> GetNamesEvent() {
        String req = "select nom from evenement";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("nom"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public ObservableList<Evenement> readEvent() {
        String req = "select * from evenement";


        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"),rs.getDate("date"), rs.getString("description"),rs.getDate("datefin"), rs.getInt("nbr_personnes")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public void suppEvenementPst(Evenement e) {
        String req = "delete from evenement where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void modifierEvenementPst(Evenement e) {
        String req = "update evenement set nom=? , lieu=?, date = ? , description= ?,datefin = ? , nbr_personnes = ?   where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, e.getNom());
              pst.setString(2, e.getLieu());
            pst.setDate(3, e.getDate());
             pst.setString(4, e.getDescription());
             pst.setDate(5, e.getDatefin());
            pst.setInt(6, e.getNbr_personnes());
           
           
            pst.setInt(7, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public int GetIdEvent(String value) {
        String req = "select id from evenement where nom = '" + value + "';";

        int id = 0;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
           
        }
        return id;
    }
 public String GetNomEventbyId(int idEvent) {
        String req = "select nom from evenement where id ='" + idEvent + "'";
        String nom = null;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                nom = rs.getString("nom");

            }

        } catch (SQLException ex) {
   
        }
        return nom;
    }
  public ObservableList<Evenement> recherche(String searchby, String value) {
        String req = "select * from evenement where " + searchby + " like '%" + value + "%'";

        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"),rs.getDate("date"), rs.getString("description"),rs.getDate("datefin"), rs.getInt("nbr_personnes")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  public ObservableList<Evenement> tri() {
      String req = "select * from evenement order by date desc";

    ObservableList<Evenement> list = FXCollections.observableArrayList();
    try {
        
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
           list.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"),rs.getDate("date"), rs.getString("description"),rs.getDate("datefin"), rs.getInt("nbr_personnes")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
}
