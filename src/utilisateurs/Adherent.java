package utilisateurs;

public class Adherent {
    private String nom;
    private String prenom;
    private static int nbrAdherents = 0;
    public static int nbr = 0;
    private int identifiant;
    private String adresse;
    private int delai;
    public static void setNbrAdherents(int nbrAdherents) {
        Adherent.nbrAdherents = nbrAdherents;
    }

    public String type;
    // private int empruntable = 0;
    private int empruntActuel = 0;
    private Emprunt[] emprunts;

    
    public Adherent() {
        this.type = "none";
    }
    public Adherent(int id, String nom, String prenom, String adresse, String type) {
        nbrAdherents++;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = id;
        this.adresse = adresse;
        this.type = type;
        if(this.type.equals("etudiant")){
            this.emprunts = new  Emprunt[2];
            this.delai = 3;
        }else if(this.type.equals("enseignant")){
            this.emprunts = new Emprunt[4];
            this.delai = 5;
        }else{
            this.emprunts = new Emprunt[1];
            this.delai = 2;
            // System.err.println("err");
        }
    }
    public int getDelai() {
        return delai;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public static int getNbrAdherents() {
        return nbrAdherents;
    }
    public int getIdentifiant() {
        return this.identifiant;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    public String getAdresse() {
        return this.adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    

    public int getEmpruntActuel() {
        return empruntActuel;
    }
    public void setEmpruntActuel(int empruntActuel) {
        this.empruntActuel = empruntActuel;
    }
    //Verifier le nombre de livre enpruntable
    public int nbrEmpruntable(){ 
        // System.out.println(this.nom + " peut effectuer: " + this.emprunts.length + " emprunts");
        return this.emprunts.length;
    }
    //Verifier le nombre d'emprunt actuel
    public void nbrEmprunt(){
        System.out.println(this.nom + " a effectuer: " + this.empruntActuel + " emprunts");
    }
    //Emprunter un livre & Rendre un livre
    public void enprunt(Emprunt emprunt){
        this.emprunts[this.empruntActuel] = emprunt;
        this.empruntActuel++;
    }
    public void rendre(Emprunt emprunt){
        for(int i=0; i<this.empruntActuel;i++){
            if(emprunt.getCode().equals(this.emprunts[i].getCode())){
                if(!(i == this.emprunts.length-1 || (this.empruntActuel < this.emprunts.length && i == this.empruntActuel-1))){
                    for(int j = i; j<this.empruntActuel-1; j++){
                        this.emprunts[j] = this.emprunts[j+1];
                    }
                }
                this.empruntActuel--;
            }
        }
    }


    public void affiche() {
        System.out.println("Adherent [nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", adresse=" + adresse
                + ", type=" + type + "]");
    }

    public void afficheEmprunt() {
        for (int i = 0; i <this.empruntActuel;i++) {
            System.out.println(this.emprunts[i].toString());
        }
    }
    @Override
    public String toString() {
        return "Adherent [nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", adresse=" + adresse
                + ", delai=" + delai + ", type=" + type + ", empruntActuel=" + empruntActuel + "]";
    }

    public boolean retard(){
        for (int i = 0; i <this.empruntActuel;i++) {
            if (this.emprunts[i].dateCompaire() == true) {
                System.err.println("Date de delivrance depasser pour le livre: "+ this.emprunts[i].getCode());
                return true;
            }
        }
        System.err.println("pb");
        return false;
    }
}
