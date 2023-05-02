package users.entity;

import java.util.Date;

public class Reclamation {
	    private int id_reclamation;
	    private int idArticle;
	    private String nom;
	    private String commentaire;
	    private String date;
	    private String respond;
	    private String email;

    // Constructeur par d�faut
    public Reclamation() {}

    // Constructeur avec param�tres
    public Reclamation(String nom, String commentaire, String date, String respond, String email) {
        this.nom = nom;
        this.commentaire = commentaire;
        this.date = date;
        this.respond = respond;
        this.email = email;
    }
    
        public Reclamation(int id_reclamation,String nom, String commentaire, String date, String respond, String email) {
        this.id_reclamation=id_reclamation;
            this.nom = nom;
        this.commentaire = commentaire;
        this.date = date;
        this.respond = respond;
        this.email = email;
    }
        

        
        
    public Reclamation(int id_reclamation,String nom) {
          this.id_reclamation = id_reclamation;
        this.nom=nom;
      
    }
        
    
    public int getIdArticle() {
        return idArticle;
    }

    // Getters et setters
    public void setIdArticle(int idArticle) {    
        this.idArticle = idArticle;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate() {
        return date;
    }

    public Reclamation(String nom, String commentaire, String respond, String email) {
		super();
		this.nom = nom;
		this.commentaire = commentaire;
		this.respond = respond;
		this.email = email;
	}

	public void setDate(String date) {
        this.date = date;
    }

    public Reclamation(String nom, String commentaire, String date, String respond, String email,int idArticle) {
		super();
		this.nom = nom;
		this.commentaire = commentaire;
		this.date = date;
		this.respond = respond;
		this.email = email;
		this.idArticle = idArticle;
	}
    
    

	public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // M�thode toString pour afficher les informations de l'objet sous forme de cha�ne de caract�res
    @Override
    public String toString() {
        return "Reclamation [id_reclamation=" + id_reclamation + ", nom=" + nom + ", commentaire=" + commentaire
                + ", date=" + date + ", respond=" + respond + ", email=" + email + "]";
    }
}
