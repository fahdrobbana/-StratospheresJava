/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.entity;

/**
 *
 * @author joujo
 */
public class Participation {

    private int id;
    private int event_id;
    private String nom, prenom, adresse, email;
     private int numero;
   

    public Participation() {
    }

    public Participation(int id, int event_id, String nom, String prenom, String adresse, String email,int numero) {
        this.id = id;
        this.event_id = event_id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numero= numero;
       
    }

    public Participation(int event_id, String nom, String prenom, String adresse, String email, int numero) {
        this.event_id = event_id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numero= numero;
       
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero=numero;
    }

    public Participation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", event_id=" + event_id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", numero=" + numero + '}';
    }

  

    
    

  
}
