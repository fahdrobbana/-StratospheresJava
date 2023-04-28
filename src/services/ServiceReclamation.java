package services;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.*;

public class ServiceReclamation implements IServiceReclamation<Reclamation> {

    private Connection cnx = DataSource.getInstance().getConnection();

    // M�thode pour ajouter une nouvelle r�clamation � la base de donn�es
    public void ajouter(Reclamation reclamation) {
        try {
            // Cr�er une requ�te SQL pour ajouter une r�clamation
            String query = "INSERT INTO reclamations (nom,commentaire,date,respond, email,idArticle) VALUES (?, ?,?,?,?,?)";
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
            String query = "DELETE FROM reclamations WHERE id_reclamation = ?";
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
    public void modifier(String nom, int id) {
        try {
            // Cr�er une requ�te SQL pour modifier une r�clamation en utilisant son nom
            String query = "UPDATE reclamations SET respond = ?  WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, nom);
            statement.setInt(2, id);

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
            String query = "SELECT * FROM reclamations";
            Statement statement = cnx.createStatement();

            // Ex�cut
            // Ex�cuter la requ�te SQL et r�cup�rer les r�sultats
            ResultSet resultSet = statement.executeQuery(query);

            // Parcourir les r�sultats et cr�er des objets Reclamation pour chaque ligne
            while (resultSet.next()) {
                int id = resultSet.getInt("id_reclamation");
                String nom = resultSet.getString("nom");
                String commentaire = resultSet.getString("commentaire");
                String date = resultSet.getString("date");
                String respond = resultSet.getString("respond");
                String email = resultSet.getString("email");
                Reclamation reclamation = new Reclamation(id, nom, commentaire, date, respond, email);
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamations;
    }

    // M�thode pour rechercher une r�clamation dans la base de donn�es en utilisant
    // son identifiant
    public Reclamation rechercherReclamationParId(int id) {
        try {
            // Cr�er une requ�te SQL pour r�cup�rer une r�clamation en utilisant son
            // identifiant
            String query = "SELECT * FROM reclamations WHERE id_reclamation = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);

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
                Reclamation reclamation = new Reclamation(id, nom, commentaire, date, respond, email);
                return reclamation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countRecArt(int art_id) {
        int count = 0;
       try {
            
            String query = "SELECT COUNT(*) FROM reclamations WHERE idArticle = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, art_id);

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
