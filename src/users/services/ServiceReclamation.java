package users.services;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import users.entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.utils.MyConnectionn;

public class ServiceReclamation implements IServiceReclamation<Reclamation> {

    private Connection cnx = MyConnectionn.getInstance().getConn();
    Reclamation r = new Reclamation();

    // M�thode pour ajouter une nouvelle r�clamation � la base de donn�es
    public void ajouter(Reclamation reclamation) {
        try {
            // Cr�er une requ�te SQL pour ajouter une r�clamation
            String query = "INSERT INTO reclamation (nom,commentaire,date,respond, email,idArticle) VALUES (?, ?,?,?,?,?)";
            System.out.println(reclamation.toString());

            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, reclamation.getNom());
            statement.setString(2, reclamation.getCommentaire());
            statement.setString(3, reclamation.getDate());

            statement.setString(4, reclamation.getRespond());
            statement.setString(5, reclamation.getEmail());
            statement.setInt(6, reclamation.getIdArticle());
            statement.executeUpdate();
            System.out.println("Reclamation ajouter avec success !");

            // Ex�cuter la requ�te SQL
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // M�thode pour supprimer une r�clamation de la base de donn�es en utilisant son
    // nom
    public void supprimer(int id) {
        try {
            // Cr�er une requ�te SQL pour supprimer une r�clamation en utilisant son nom
            String query = "DELETE FROM reclamation WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);

            // Ex�cuter la requ�te SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // M�thode pour modifier une r�clamation de la base de donn�es en utilisant son
    // nom
    public void modifier(int id_reclamation,String nom) {
        try {
            // Cr�er une requ�te SQL pour modifier une r�clamation en utilisant son nom
            String query = "UPDATE reclamation SET respond = ?  WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
          
            statement.setInt(1, r.getId_reclamation());
              statement.setString(2, r.getNom());

            System.out.println("Reclamation ajout� avec succ�s !");
            // Ex�cuter la requ�te SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modif(String respond,int id_reclamation)
    {
            try {
            // Cr�er une requ�te SQL pour modifier une r�clamation en utilisant son nom
            String query = "UPDATE reclamation SET respond = ?  WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, r.getId_reclamation());
             statement.setString(2, r.getRespond());

            System.out.println("Reclamation ajout� avec succ�s !");
            // Ex�cuter la requ�te SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // M�thode pour afficher toutes les r�clamations stock�es dans la base de
    // donn�es
    public ObservableList<Reclamation> afficherTous() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            // Cr�er une requ�te SQL pour r�cup�rer toutes les r�clamations
            String query = "SELECT * FROM reclamation";
            Statement statement = cnx.createStatement();

            // Ex�cut
            // Ex�cuter la requ�te SQL et r�cup�rer les r�sultats
            ResultSet resultSet = statement.executeQuery(query);

            // Parcourir les r�sultats et cr�er des objets Reclamation pour chaque ligne
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String commentaire = resultSet.getString("commentaire");
                String date = resultSet.getString("date");
                String respond = resultSet.getString("respond");
                String email = resultSet.getString("email");
                Reclamation reclamation = new Reclamation( nom, commentaire, date, respond, email);
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamations;
    }

    // M�thode pour rechercher une r�clamation dans la base de donn�es en utilisant
    // son identifiant
    public Reclamation rechercherReclamationParId(int id_reclamation) {
        try {
            // Cr�er une requ�te SQL pour r�cup�rer une r�clamation en utilisant son
            // identifiant
            String query = "SELECT * FROM reclamation WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id_reclamation);

            // Ex�cuter la requ�te SQL et r�cup�rer le r�sultat
            ResultSet resultSet = statement.executeQuery();

            // Si la requ�te a retourn� un r�sultat, cr�er un objet Reclamation avec les
            // donn�es et le retourner
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String commentaire = resultSet.getString("commentaire");
                String date = resultSet.getString("date");
                String respond = resultSet.getString("respond");
                String email = resultSet.getString("email");
                Reclamation reclamation = new Reclamation(id_reclamation, nom, commentaire, date, respond, email);
                return reclamation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countRecArt(int id_article) {
        int count = 0;
       try {
            
            String query = "SELECT COUNT(*) FROM reclamation WHERE idArticle = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, id_article);

            // Exécuter la requête SQL
            ResultSet rs = stmt.executeQuery();

            // Récupérer le nombre de lignes
            if (rs.next()) {
                count = rs.getInt(1);
            }

            // Fermer les ressources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
