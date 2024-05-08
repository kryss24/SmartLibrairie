package documents;

public class ListeLivre {
    private Document[] livreArray;
    private int nombreLivre = 0;

    public ListeLivre(int size) {
        this.livreArray = new Document[size];
    }

    public void add(Document ad) {
        this.livreArray[nombreLivre] = ad;
        this.nombreLivre++;
    }

    public void delete(String str) {
        for (int i = 0; i < this.nombreLivre; i++) {
            if (this.livreArray[i].getCode().equals(str)) {
                if (!(i == this.livreArray.length - 1
                        || (this.nombreLivre < this.livreArray.length && i == this.nombreLivre - 1))) {
                    for (int j = i; j < this.nombreLivre - 1; j++) {
                        this.livreArray[j] = this.livreArray[j + 1];
                    }
                }
                this.nombreLivre--;
            }
        }
    }

    public int getNombreLivre() {
        return nombreLivre;
    }

    public Document searchDocument(String value) {
        Document document = new Document("", new int[] { 0, 0 }, 0);
        for (int i = 0; i < this.nombreLivre; i++) {
            // System.out.println(this.livreArray[i].getIdentifiant() ==
            // Integer.parseInt(value));
            if (this.livreArray[i].getClass().getSimpleName().equals(value)
                    || this.livreArray[i].getTitle().equals(value) || this.livreArray[i].getCode().equals(value)
                    || this.livreArray[i].getLocalisation().equals(new int[] {Integer.parseInt(value),Integer.parseInt(value)})) {
                return this.livreArray[i];
            }
        }
        return document;
    }

    public void affiche() {
        for (int i = 0; i < this.nombreLivre; i++) {
            this.livreArray[i].affiche();
        }
    }
}
