package users.services;

import java.sql.Connection;
import java.util.List;

import users.entity.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.utils.MyConnectionn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceArticle implements IServiceArticle<Article> {

	private Connection cnx = MyConnectionn.getInstance().getConn();

	// M�thode pour ajouter un nouvel article � la base de donn�es
	public void ajouter(Article article) {
		try {
			String requete = "INSERT INTO articles (id_article,type,id_reclamation, image, description) VALUES (?, ?, ?, ?,?)";
			PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, article.getId_article());
			pst.setString(2, article.getType());
                         pst.setInt(3, article.getId_reclamation());
			
			pst.setString(4, article.getImage());
			pst.setString(5, article.getDescription());
			pst.executeUpdate();
			System.out.println("Article ajout� avec succ�s !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// M�thode pour supprimer un article de la base de donn�es en utilisant son type
	public void supprimer(int id) {
		try {
			String requete = "DELETE FROM articles WHERE id_article = ?";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Article supprim� avec succ�s !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// M�thode pour modifier un article de la base de donn�es en utilisant son type
	public void modifier(String type, int id) {
		try {
			String requete = "UPDATE articles SET type = ?  WHERE id_article = ?";
			PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, type);

			pst.setInt(2, id);

			pst.executeUpdate();
			System.out.println("Article modifi� avec succ�s !");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// M�thode pour afficher tous les articles stock�s dans la base de donn�es
	public ObservableList<Article> afficherTous() {
		ObservableList<Article> articles = FXCollections.observableArrayList();
		;
		try {
			String requete = "SELECT * FROM articles";
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(requete);
			while (rs.next()) {
				Article article = new Article();
				article.setId_article(rs.getInt("id_article"));
				article.setType(rs.getString("type"));
//				article.setId_reclamation(rs.getInt("id_reclamation"));
				article.setImage(rs.getString("image"));
				article.setDescription(rs.getString("description"));
				articles.add(article);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return articles;
	}

	// M�thode pour rechercher un article dans la base de donn�es en utilisant son
	// identifiant
	public Article rechercherArticleParId(int id) {
		Article article = null;
		try {
			String requete = "SELECT * FROM articles WHERE id_article = ?";
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
