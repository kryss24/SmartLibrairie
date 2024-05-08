package documents;

public class Livre extends Article{
    private String nomEditeur;

    //Constructeur
    public Livre(String title, int[] localisation, int nbrExemplaires, String nomAuteur, String datePublication, String nomEditeur) {
        super(title, localisation, nbrExemplaires, nomAuteur, datePublication);
        this.nomEditeur = nomEditeur;
    }
    public Livre(Article article, String nomEditeur){
        super(article);
        this.nomEditeur = nomEditeur;
    }

    //Acesseur
    public String getNomEditeur() {
        return this.nomEditeur;
    }
    public void setNomEditeur(String nomEditeur){
        this.nomEditeur = nomEditeur;
    }

    public void affiche() {
        System.out.println(super.toString() + ", nomEditeur=" + nomEditeur + ", type= '" + this.getClass().getSimpleName() + "'");
    }
    
}
