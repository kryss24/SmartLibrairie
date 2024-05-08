package documents;
// package document;

public class Magazine extends Document{
    private int frequency;
    
    
    //Constructeur
    public Magazine(String title, int[] localisation, int nbrExemplaires, int frequency) {
        super(title, localisation, nbrExemplaires);
        this.frequency = frequency;
    }

    //Acesseur
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    public int getFrequency() {
        return frequency;
    }

    public void affiche() {
        System.out.println(super.toString() + ", frequency=" + frequency + ", type= '" + this.getClass().getSimpleName() + "'");
    }

    
}
