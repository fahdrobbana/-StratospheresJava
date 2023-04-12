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

	// Méthode pour ajouter une nouvelle réclamation à la base de données
	public void ajouter(Reclamation reclamation) {
		try {
			// Créer une requête SQL pour ajouter une réclamation
			String query = "INSERT INTO reclamations (nom,commentaire,date,respond, email) VALUES (?, ?,?,?,?)";
			System.out.println(reclamation.toString());

			PreparedStatement statement = cnx.prepareStatement(query);
			statement.setString(1, reclamation.getNom());
			statement.setString(2, reclamation.getCommentaire());
			statement.setString(3, reclamation.getDate());

			statement.setString(4, reclamation.getRespond());
			statement.setString(5, reclamation.getEmail());
			statement.executeUpdate();
			System.out.println("Reclamation ajouter avec success !");

			// Exécuter la requête SQL

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour supprimer une réclamation de la base de données en utilisant son
	// nom
	public void supprimer(int id) {
		try {
			// Créer une requête SQL pour supprimer une réclamation en utilisant son nom
			String query = "DELETE FROM reclamations WHERE id_reclamation = ?";
			PreparedStatement statement = cnx.prepareStatement(query);
			statement.setInt(1, id);

			// Exécuter la requête SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour modifier une réclamation de la base de données en utilisant son
	// nom
	public void modifier(String nom,int id) {
		try {
			// Créer une requête SQL pour modifier une réclamation en utilisant son nom
			String query = "UPDATE reclamations SET respond = ?  WHERE id_reclamation = ?";
			PreparedStatement statement = cnx.prepareStatement(query);
			statement.setString(1,nom);
			statement.setInt(2, id);
		 
			System.out.println("Reclamation ajouté avec succès !");
			// Exécuter la requête SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour afficher toutes les réclamations stockées dans la base de
	// données
	public ObservableList<Reclamation> afficherTous() {
		ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
		try {
			// Créer une requête SQL pour récupérer toutes les réclamations
			String query = "SELECT * FROM reclamations";
			Statement statement = cnx.createStatement();

			// Exécut
			// Exécuter la requête SQL et récupérer les résultats
			ResultSet resultSet = statement.executeQuery(query);

			// Parcourir les résultats et créer des objets Reclamation pour chaque ligne
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

	// Méthode pour rechercher une réclamation dans la base de données en utilisant
	// son identifiant
	public Reclamation rechercherReclamationParId(int id) {
		try {
			// Créer une requête SQL pour récupérer une réclamation en utilisant son
			// identifiant
			String query = "SELECT * FROM reclamations WHERE id_reclamation = ?";
			PreparedStatement statement = cnx.prepareStatement(query);
			statement.setInt(1, id);

			// Exécuter la requête SQL et récupérer le résultat
			ResultSet resultSet = statement.executeQuery();

			// Si la requête a retourné un résultat, créer un objet Reclamation avec les
			// données et le retourner
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
}
