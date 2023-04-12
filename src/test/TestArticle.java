package test;

import java.util.List;

import entities.Article;
import services.IServiceArticle;
import services.ServiceArticle;
 

public class TestArticle {
	 

	    public static void main(String[] args) {
	        IServiceArticle<Article> service = new ServiceArticle();

	        TestArticle testArticle = new TestArticle();

	        // Ajouter un nouvel article
	        Article article1 = new Article("type3", 2, "image1.jpg", "description1");
	        service.ajouter(article1);

	        // Modifier un article
	        service.modifier("type3", 24);

	        // Afficher tous les articles
	        List<Article> articles = service.afficherTous();
	        for (Article article : articles) {
	            System.out.println(article);
	        }

	        // Rechercher un article par ID
	        Article article2 = service.rechercherArticleParId(article1.getId_article());
	        System.out.println(article2);

	        // Supprimer un article
	        service.supprimer(3);
	    }

	 
}
