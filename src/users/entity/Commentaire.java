/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.entity;

import java.util.Date;


//import java.sql.Date;

/**
 *
 * @author Amoulette
 */
public class Commentaire {
    
   int  id;	
   String   titre;	;
    private int id_annonce;
   String nom ,text;

    public Commentaire(String titre, String nom, String text) {
        this.titre = titre;
        this.nom = nom;
        this.text = text;
    }
   Date date;	

    public Commentaire(int id, int id_annonce, String nom, String text, Date date) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.text = text;
        this.date = date;
    }

    public Commentaire() {
    }

    public Commentaire(int id_annonce, String nom, String text, Date date) {
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.text = text;
        this.date = date;
    }

    public Commentaire(String nom, String text, Date date) {
        this.nom = nom;
        this.text = text;
        this.date = date;
    }

    public Commentaire(int id, int id_annonce, String nom, String text) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.text = text;
    }

    public Commentaire(int id_annonce, String nom, String text) {
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.text = text;
    }

   /* public Commentaire(int id, int id_annonce, String nom, String text, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public String toString() {
        return "Commentaire{" + "id_annonce=" + id_annonce + ", nom=" + nom + ", text=" + text + '}';
    }


   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
