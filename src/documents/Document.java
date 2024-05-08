package documents;

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

}
