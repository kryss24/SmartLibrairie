package documents;

public class Memoire extends Document {
    private String nomCandidat; //Pour le nom du candidat
    private String dateSoutenance; //Pour la date soutenance

    //Constructeur
    public Memoire(String title, int[] localisation, int nbrExemplaires, String nomCandidat, String dateSoutenance) {
        super(title, localisation, nbrExemplaires);
        this.nomCandidat = nomCandidat;
        this.dateSoutenance = dateSoutenance;
    }

    //accesseur
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
        System.out.println(super.toString() + ", nomCandidat=" + nomCandidat + ", dateSoutenance=" + dateSoutenance + ", type= '" + this.getClass().getSimpleName() + "'");
    }
}
