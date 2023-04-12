package test;

import java.util.List;

import entities.Reclamation;
import services.IServiceReclamation;
import services.ServiceReclamation;

public class TestReclamation {
    
    public static void main(String[] args) {
    	IServiceReclamation<Reclamation> service = new ServiceReclamation();
    // Ajouter une r�clamation
    	
    Reclamation rec = new Reclamation("medd", "commentaire1","respond1", "email1");
    service.ajouter(rec);

    // Afficher toutes les r�clamations
    List<Reclamation> reclamations = service.afficherTous();
    for (Reclamation r : reclamations) {
        System.out.println(r);
    }

    // Modifier une r�clamation
    service.modifier("medd",6);

    // Rechercher une r�clamation par identifiant
    Reclamation r = service.rechercherReclamationParId(1);
    System.out.println(r);

    // Supprimer une r�clamation
    service.supprimer(6);
 
}
}
