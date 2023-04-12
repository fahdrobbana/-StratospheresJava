/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a31;

import java.security.Provider;
import java.util.Date;
import workshop3a31.entities.Personne;
import workshop3a31.services.ServicePersonne;
import workshop3a31.utils.MyDB;
import workshop3a31.entities.Annonce;
import workshop3a31.services.ServiceAnnonce;
import workshop3a31.services.ServiceCommentaire;
import workshop3a31.entities.Commentaire;
//import java.sql.Date;
/**
 *
 * @author Andrew
 */
public class Workshop3A31 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MyDB.createorgetInstance();
       /* Annonce a2 = new Annonce("nn", "nn", "nn", "nn", 10, "nn", "ss", "etat", "categorie");
        Annonce a3 = new Annonce( "amal", "image", "descreption", "titre", 10, "email", "ss", "etat", "categorie");
         Annonce a4 = new Annonce("chihemek", "image", "descreption", "titre", 10, "email", "ss", "etat", "categorie");
        Annonce a5 = new Annonce(112,"salma", "image", "descreption", "titre", 10, "email", "ss", "etat", "categorie");*/
        // Date date = new Date(); // current date and time
      //  Commentaire c1 =new Commentaire(4, 104, "haha", "lol", date );
        

        ServiceAnnonce sp = new ServiceAnnonce();
        ServiceCommentaire sc = new ServiceCommentaire();

      //  sp.Ajouter(a4);
     // sp.Supprimer(a5);//naamel naati l id 
   //        sp.Modifier(a3);
       // System.out.println(sp.Afficher());
       
       
          // sc.Ajouter(c1);
   
      // System.out.println(sp.readAnnonce());
    }

}
