package utilisateurs;

public class ListeAdherent {
    private Adherent[] adhArrays;
    private int nombreAdherent = 0;

    public ListeAdherent(int size) {
        this.adhArrays = new Adherent[size];
    }
    public void add(Adherent ad){
        this.adhArrays[nombreAdherent] = ad;
        this.nombreAdherent++;
    }
    public void delete(int id){
        for(int i=0; i<this.nombreAdherent;i++){
            if(id == this.adhArrays[i].getIdentifiant()){
                if(!(i == this.adhArrays.length-1 || (this.nombreAdherent < this.adhArrays.length && i == this.nombreAdherent-1))){
                    for(int j = i; j<this.nombreAdherent-1; j++){
                        this.adhArrays[j] = this.adhArrays[j+1];
                    }
                }
                this.nombreAdherent--;
            }
        }
    }
    public int getNombreAdherent() {
        return nombreAdherent;
    }
    public Adherent searchAdherent(String value){
        Adherent adherent = new Adherent();
        try {
            for(int i=0;i<this.nombreAdherent;i++){
                // System.out.println(this.adhArrays[i].getIdentifiant() == Integer.parseInt(value));
                if (this.adhArrays[i].type.equals(value) || this.adhArrays[i].getAdresse().equals(value) || this.adhArrays[i].getNom().equals(value) || this.adhArrays[i].getPrenom().equals(value)){
                    return this.adhArrays[i];
                }else if(this.adhArrays[i].getIdentifiant() == Integer.parseInt(value)){
                    return this.adhArrays[i];
                }
            }
        } catch (Exception e) {
            return adherent;
        }
        return adherent;
    }
    public void affiche(){
        for(int i=0; i < this.nombreAdherent; i++){
            adhArrays[i].affiche();
        }
    }
}
