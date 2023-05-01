/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.entity;

import java.sql.Date;

/**
 *
 * @author joujo
 */
public class Evenement {
    
      private int id;
    String nom;
    private String lieu;
    private Date date;
    private String description;

    private Date datefin;
    private int nbr_personnes;
    

    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(int id, String nom, String lieu, Date date, String description, Date datefin, int nbr_personnes) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
        this.nbr_personnes = nbr_personnes;
    }

    public Evenement(String nom, String lieu, Date date, String description,Date datefin, int nbr_personnes) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
        this.nbr_personnes = nbr_personnes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", description=" + description +", datefin=" + datefin + ", nbr_personnes=" + nbr_personnes + '}';
    }
    

    
}
