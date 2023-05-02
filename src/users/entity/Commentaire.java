/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.entity;


import java.sql.Date;

//import java.sql.Date;

/**
 *
 * @author Amoulette
 */
public class Commentaire {
    
   int  id ;	
  // String   titre,email;
    private int id_annonce;
      int   user_id ;
  
   String nom ,text;
   Date date;

   public Commentaire() {
    }
   
    

    public Commentaire(int id_annonce, int user_id,  String nom, String text, Date date) {
        this.id_annonce = id_annonce;
        this.user_id = user_id;
        this.nom = nom;
        this.text = text;
        this.date = date;
    }

    public Commentaire(int id, int id_annonce, int user_id, String nom, String text, Date date) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.user_id = user_id;
        this.nom = nom;
        this.text = text;
        this.date = date;
    }
    
    public Commentaire(int id_annonce, String nom, String text, Date date) {
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.text = text;
        this.date = date;
    }


    @Override
    public String toString() {
        return "Commentaire{" + "id_annonce=" + id_annonce + ", user_id=" + user_id + ", nom=" + nom + ", text=" + text + ", date=" + date + '}';
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
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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