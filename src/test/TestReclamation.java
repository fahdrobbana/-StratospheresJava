package test;

import java.util.List;

import entities.Reclamation;
import services.IServiceReclamation;
import services.ServiceReclamation;

public class TestReclamation {
    
    public static void main(String[] args) {
    	IServiceReclamation<Reclamation> service = new ServiceReclamation();
    // Ajouter une réclamation
    	
    Reclamation rec = new Reclamation("medd", "commentaire1","respond1", "email1");
    service.ajouter(rec);

    // Afficher toutes les réclamations
    List<Reclamation> reclamations = service.afficherTous();
    for (Reclamation r : reclamations) {
        System.out.println(r);
    }

    // Modifier une réclamation
    service.modifier("medd",6);

    // Rechercher une réclamation par identifiant
    Reclamation r = service.rechercherReclamationParId(1);
    System.out.println(r);

    // Supprimer une réclamation
    service.supprimer(6);
 
}
}
