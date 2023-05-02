package users.entity;


public class Article {
    private int id_article;
    private String type;
    private int id_reclamation;
    private String image;
    private String description;

    // Constructeur par d�faut
    public Article() {}

    public Article(String type, String image, String description) {
        this.type = type;
        this.image = image;
        this.description = description;
    }
    

    
    

    // Constructeur avec param�tres
    public Article(int id_article, String type, String image, String description) {
        this.id_article = id_article;
        this.type = type;
    
        this.image = image;
        this.description = description;
    }

    // Getters et setters
    public int getId_article() {
        return id_article;
    }


	public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // M�thode toString pour afficher les informations de l'objet sous forme de cha�ne de caract�res
    @Override
    public String toString() {
        return "Article [id_article=" + id_article + ", type=" + type + ", id_reclamation=" + id_reclamation
                + ", image=" + image + ", description=" + description + "]";
    }
}
