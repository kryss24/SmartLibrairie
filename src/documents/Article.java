package documents;
import java.util.Scanner;
public class Article extends Document{//Vas heriter de tout les attributs de Document
    private String nomAuteur; //Pour le nom de l'auteur
    private String datePublication; //Pour la date publication

    //Constructeur
    public Article(String title, int[] localisation, int nbrExemplaires, String nomAuteur, String datePublication) {
        super(title, localisation, nbrExemplaires);
        this.nomAuteur = nomAuteur;
        this.datePublication = datePublication;
    }
    

    public Article(String title, int[] localisation, int nbrExemplaires, String code, String nomAuteur,
            String datePublication) {
        super(title, localisation, nbrExemplaires, code);
        this.nomAuteur = nomAuteur;
        this.datePublication = datePublication;
    }


    public Article(Document doc, String nomAuteur, String datePublication) {
        super(doc);
        this.nomAuteur = nomAuteur;
        this.datePublication = datePublication;
    }

    public Article(Article article){
        super(article.getTitle(), article.getLocalisation(), article.getNbrExemplaires());
        this.nomAuteur = article.getNomAuteur();
        this.datePublication = article.getDatePublication();
    }

    //accesseur
    public String getNomAuteur() {
        return this.nomAuteur;
    }
    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }
    public String getDatePublication() {
        return this.datePublication;
    }
    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public void affiche() {
        System.out.println(super.toString() + ", nomAuteur='" + nomAuteur + "', datePublication='" + datePublication +"', type= '" + this.getClass().getSimpleName() + "'"); 
    }

    @Override
    public String toString() {
        return super.toString() + ", nomAuteur=" + nomAuteur + ", datePublication=" + datePublication + "]";
    }

    public static Article insert(){
        Document doc = Document.insert();
        System.out.print("Entrer le nom de l'auteur");
        String nom = (new Scanner(System.in)).nextLine();
        System.out.print("Entrer la date de publication (yyyy-mm-dd): ");
        String datePublication = (new Scanner(System.in)).nextLine();
        return (new Article(doc, nom, datePublication));
    }
}
