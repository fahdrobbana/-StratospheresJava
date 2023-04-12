/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author joujo
 */
public class ParticipationService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ParticipationService() {
        conn = DataSource.getInstance().getCnx();
    }

    public boolean ajouterParticipation(Participation p) {

        String req = "insert into participation (nom,prenom,adresse,email,event_id) values (?,?,?,?,?)";
        if (checkfull(p.getEvent_id())) {
            try {
                pst = conn.prepareStatement(req);

                pst.setString(1, p.getNom());
                pst.setString(2, p.getPrenom());
                pst.setString(3, p.getAdresse());
                pst.setString(4, p.getEmail());
                pst.setInt(5, p.getEvent_id());
                pst.executeUpdate();

                return true;

            } catch (SQLException ex) {
                Logger.getLogger(ParticipationService.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        } else {
            return false;
        }
    }

    public int CountNbrPartic(int id) {
        int nbr = 0;
        String req = "select count(id) as nbr_participation  from participation where event_id =" + id + "";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                nbr = rs.getInt("nbr_participation");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbr;
    }

    public int Nb_personnes(int id) {
        int nbr = 0;
        String req = "select nbr_personnes from evenement where id=" + id + "";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                nbr = rs.getInt("nbr_personnes");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbr;
    }

    public boolean checkfull(int id) {
        if ((CountNbrPartic(id)) < (Nb_personnes(id))) {
            return true;
        } else {
            return false;
        }
    }

 
    public ObservableList<Participation> readPraticipation() {
        String req = "select * from participation";

        ObservableList<Participation> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Participation(rs.getInt("id"), rs.getInt("event_id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void modifierParticipationPst(Participation p) {
        String req = "update participation set nom= ?,  prenom= ?, adresse= ?, email= ?, event_id= ? where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getAdresse());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getEvent_id());
            pst.setInt(6, p.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suppParticipationPst(Participation p) {
        String req = "delete from participation where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
