package documents;
import java.util.Scanner;
public class Memoire extends Document {
    private String nomCandidat; // Pour le nom du candidat
    private String dateSoutenance; // Pour la date soutenance

    // Constructeur
    public Memoire(String title, int[] localisation, int nbrExemplaires, String nomCandidat, String dateSoutenance) {
        super(title, localisation, nbrExemplaires);
        this.nomCandidat = nomCandidat;
        this.dateSoutenance = dateSoutenance;
    }
    
    public Memoire(String title, int[] localisation, int nbrExemplaires, String code, String nomCandidat,
            String dateSoutenance) {
        super(title, localisation, nbrExemplaires, code);
        this.nomCandidat = nomCandidat;
        this.dateSoutenance = dateSoutenance;
    }

    public Memoire(Document document, String nomCandidat, String dateSout) {
        super(document);
        this.nomCandidat = nomCandidat;
        this.dateSoutenance = dateSout;
    }

    // accesseur
    public String getNomCandidat() {
        return this.nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getdateSoutenance() {
        return this.dateSoutenance;
    }

    public void setdateSoutenance(String dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
    }

    public void affiche() {
        System.out.println(super.toString() + ", nomCandidat=" + nomCandidat + ", dateSoutenance=" + dateSoutenance
                + ", type= '" + this.getClass().getSimpleName() + "'");
    }

    public static Memoire insert() {
        Document doc = Document.insert();
        System.out.print("Entrer le nom du candidat");
        String nom = (new Scanner(System.in)).nextLine();
        System.out.print("Entrer la date de soutenance: ");
        String datePublication = (new Scanner(System.in)).nextLine();
        return (new Memoire(doc, nom, datePublication));
    }
}
