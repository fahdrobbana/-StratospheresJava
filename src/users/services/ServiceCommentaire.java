/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.services;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import users.entity.Commentaire;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.utils.MyConnectionn;

/**
 *
 * @author Amoulette
 */
public class ServiceCommentaire implements IService<Commentaire> {

    Connection con;
    Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;

    public ServiceCommentaire() {

        con = MyConnectionn.getInstance().getConn();

    }

    @Override
    public void Ajouter(Commentaire t) {
        /*   try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
            String req = "INSERT INTO `commentaire` (`id`, `id_annonce`, `nom`, `text`, `date`) VALUES ('"+t.getId()+"','"+t.getId_annonce()+"','"+t.getNom()+"',"+t.getText()+"','"+t.getDate()+"');";
            
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO .`commentaire` (`id_annonce`,`nom`,`text`) VALUES (?,?,?);");
            pre.setInt(1, t.getId_annonce());
            pre.setString(2, t.getNom());
            pre.setString(3, t.getText());

            pre.executeUpdate();
            System.out.println("commentaire ajoutée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Commentaire t) {
        try {
            String requete = "UPDATE `commentaire` SET"
                    + " `id_annonce`=?,"
                    + " `nom`=?,"
                    + "`text`=? where `id`=?";
            PreparedStatement pre = con.prepareStatement(requete);

            pre.setInt(1, t.getId_annonce());
            pre.setString(2, t.getNom());
            pre.setString(3, t.getText());
            pre.setInt(4, t.getId());

            pre.executeUpdate();
            System.out.println("comm modifié avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(Commentaire t) {
        String req = "delete from commentaire where id = ?";

        try {
            pre = con.prepareStatement(req);
            pre.setInt(1, t.getId());
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Commentaire> readAnnonce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Commentaire> readcomm() {
        String req = "select * from commentaire";

        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        try {
            ste = con.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                String text = rs.getString("text");
            
               // text = filterBadWords(text);
                list.add(new Commentaire(rs.getInt("id"), rs.getInt("id_annonce"), rs.getString("nom"), rs.getString("text")));//, rs.getDate("date")
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  /*  public String filterBadWords(String text) {
    ProfanityFilter filter = new ProfanityFilter();
    return filter.censor(text);
}*/
    
    @Override
    public ArrayList<Commentaire> Afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

/*  @Override
    public ArrayList<Commentaire> Afficher() {
         ArrayList<Commentaire> pers = new ArrayList<>();
        try {
            ste = con.createStatement();
            String req = "SELECT * FROM `commentaire`";
            ResultSet res = ste.executeQuery(req);

            while (res.next()) {
                int id = res.getInt("id");
                int id_annonce=res.getInt("id_annonce");// naamel afficher fel annonce wala t3ayet lel tostring 
                String nom = res.getString(3);
                String text = res.getString(4);
                Date date = res.getDate(5);

            //    Commentaire p = new Commentaire(id,id_annonce,nom,text,date); erreur
             //   pers.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pers;
    }*/
