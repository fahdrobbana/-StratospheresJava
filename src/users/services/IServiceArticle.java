package users.services;

import java.util.List;

public interface IServiceArticle<T> {
	void ajouter(T t);

	void supprimer(int id);

	void modifier(String type, int id);

	List<T> afficherTous();

	T rechercherArticleParId(int id);
}
