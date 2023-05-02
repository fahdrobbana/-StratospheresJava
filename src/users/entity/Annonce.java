/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.entity;

/**
 *
 * @author Amoulette
 */
public class Annonce {
    
    int id , user_id;
    String nom ,image,descreption,titre;
    int tel ;
    String email,local,etat,categorie;
    
    public Annonce() {
       
    }


   /* public Annonce(int id, String nom, String image, String descreption, String titre, int tel, String email, String local, String etat, String categorie) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }
*/
   

    public Annonce(int id, int user_id, String nom, String image, String descreption, String titre, int tel, String email, String local, String etat, String categorie) {
        this.id = id;
        this.user_id = user_id;
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }

   /* public Annonce(String nom, String image, String descreption, String titre, String email, String local, String etat, String categorie) {
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }*/

    public Annonce(String nom, String image, String descreption, String titre, int tel, String email, String local, String etat, String categorie) {
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }

    public Annonce(int user_id, String nom, String image, String descreption, String titre, int tel, String email, String local, String etat, String categorie) {
        this.user_id = user_id;
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }

    
    

    @Override
    public String toString() {
        return "Annonce{" + "user_id=" + user_id + ", nom=" + nom + ", image=" + image + ", descreption=" + descreption + ", titre=" + titre + ", tel=" + tel + ", email=" + email + ", local=" + local + ", etat=" + etat + ", categorie=" + categorie + '}';
    }

    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescreption() {
        return descreption;
    }

    public String getTitre() {
        return titre;
    }

    public int getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getLocal() {
        return local;
    }

    public String getEtat() {
        return etat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
 public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
   
    
}