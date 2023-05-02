/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package users.services;

import java.util.List;

public interface IServiceReclamation<T> {
	void ajouter(T t);

	void supprimer(int id_reclamation);

	void modifier( int id_reclamation,String nom);

	List<T> afficherTous();

	T rechercherReclamationParId(int id_reclamation);

}
