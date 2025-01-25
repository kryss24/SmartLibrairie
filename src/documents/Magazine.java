package documents;

import java.util.Scanner;

public class Magazine extends Document {
    private int frequency;

    // Constructeur
    public Magazine(String title, int[] localisation, int nbrExemplaires, int frequency) {
        super(title, localisation, nbrExemplaires);
        this.frequency = frequency;
    }

    public Magazine(String title, int[] localisation, int nbrExemplaires, String code, int frequency) {
        super(title, localisation, nbrExemplaires, code);
        this.frequency = frequency;
    }

    public Magazine(Document document, int frequency) {
        super(document);
        this.frequency = frequency;
    }

    // Acesseur
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void affiche() {
        System.out.println(
                super.toString() + ", frequency=" + frequency + ", type= '" + this.getClass().getSimpleName() + "'");
    }

    @SuppressWarnings("resource")
    public static Magazine insert() {
        Document doc = Document.insert();
        System.out.print("Entrer la frequence de parution: ");
        int frequency = (new Scanner(System.in)).nextInt();
        return (new Magazine(doc, frequency));
    }
}
