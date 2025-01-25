package documents;

// import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.util.*;

public class Document {
    private String title;
    private int[] localisation;
    private String code;
    private int nbrExemplaires;
    public static int nbrDocument = 0;

    // Constructeurs
    public Document(String title, int[] localisation, int nbrExemplaires) {
        nbrDocument++;
        this.title = title;
        this.localisation = localisation;
        this.code = "code" + nbrDocument;
        this.nbrExemplaires = nbrExemplaires;
    }
    public Document(){}
    public Document(String title, int[] localisation, int nbrExemplaires, String code) {
        nbrDocument++;
        this.title = title;
        this.localisation = localisation;
        this.code = code;
        this.nbrExemplaires = nbrExemplaires;
    }
    public Document(Document doc){
        this.title = doc.title;
        this.localisation = doc.localisation;
        this.code = doc.code;
        this.nbrExemplaires = doc.nbrExemplaires;
    }

    // Accesseur
    public int getNbrExemplaires() {
        return this.nbrExemplaires;
    }

    public int[] getLocalisation() {
        return this.localisation;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCode() {
        return this.code;
    }

    // Modificateur
    public void setNbrExemplaires(int nbrExemplaires) {
        this.nbrExemplaires = nbrExemplaires;
    }

    public static int getNbrDocument() {
        return nbrDocument;
    }

    public static void setNbrDocument(int nbrDocument) {
        Document.nbrDocument = nbrDocument;
    }

    public void setLocalisation(int[] localisation) {
        this.localisation = localisation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void affiche() {
        System.out.println("Code: " + this.code + "");
    }

    @Override
    public String toString() {
        return "code='" + this.code + "', title='" + this.title + "', localisation=[salle:" + this.localisation[0] +",rayon:" + this.localisation[1] + "]"
                + ", nbrExemplaires=" + nbrExemplaires + "]";
    }
    public static Document insert(){
        System.out.print("Entrer le titre: ");
        String titre = (new Scanner(System.in)).nextLine();
        int[] localisation = new int[2];
        System.out.print("Entrer la salle: ");
        localisation[0] = (new Scanner(System.in)).nextInt();
        localisation[1] = (new Scanner(System.in)).nextInt();
        System.out.print("Entrer le nombre d'exemplaire: ");
        int nbre = (new Scanner(System.in)).nextInt();

        return (new Document(titre, localisation, nbre));
    }
}
