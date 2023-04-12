package services;

import java.sql.Connection;
import java.util.List;

import entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceArticle implements IServiceArticle<Article> {

	private Connection cnx = DataSource.getInstance().getConnection();

	// Méthode pour ajouter un nouvel article à la base de données
	public void ajouter(Article article) {
		try {
			String requete = "INSERT INTO article (type, id_reclamation, image, description) VALUES (?, ?, ?, ?)";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, article.getType());
			pst.setInt(2, article.getId_reclamation());
			pst.setString(3, article.getImage());
			pst.setString(4, article.getDescription());
			pst.executeUpdate();
			System.out.println("Article ajouté avec succès !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Méthode pour supprimer un article de la base de données en utilisant son type
	public void supprimer(int id) {
		try {
			String requete = "DELETE FROM article WHERE id_article = ?";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Article supprimé avec succès !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Méthode pour modifier un article de la base de données en utilisant son type
	public void modifier(String type, int id) {
		try {
			String requete = "UPDATE article SET type = ?  WHERE id_article = ?";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, type);

			pst.setInt(2, id);

			pst.executeUpdate();
			System.out.println("Article modifié avec succès !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Méthode pour afficher tous les articles stockés dans la base de données
	public ObservableList<Article> afficherTous() {
		ObservableList<Article> articles = FXCollections.observableArrayList();
		;
		try {
			String requete = "SELECT * FROM article";
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(requete);
			while (rs.next()) {
				Article article = new Article();
				article.setId_article(rs.getInt("id_article"));
				article.setType(rs.getString("type"));
				article.setId_reclamation(rs.getInt("id_reclamation"));
				article.setImage(rs.getString("image"));
				article.setDescription(rs.getString("description"));
				articles.add(article);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return articles;
	}

	// Méthode pour rechercher un article dans la base de données en utilisant son
	// identifiant
	public Article rechercherArticleParId(int id) {
		Article article = null;
		try {
			String requete = "SELECT * FROM article WHERE id_article = ?";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				article = new Article();
				article.setId_article(rs.getInt("id_article"));
				article.setType(rs.getString("type"));
				article.setId_reclamation(rs.getInt("id_reclamation"));
				article.setImage(rs.getString("image"));
				article.setDescription(rs.getString("description"));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return article;
	}

}
