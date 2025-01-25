import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDate;

import documents.*;
import utilisateurs.*;

public class Main {
    private static Document[] document = null;

    private static int menu(int x) { // Menue Qui gere les adherents
        Scanner in = new Scanner(System.in);
        int choice = 0;
        switch (x) {
            case 0:
                System.out.println("\t\t----------------------------------------------------");
                System.out.println("\t\t|\t1. Ajouter Adherent                        |");
                System.out.println("\t\t|\t2. Supprimer Adherent                      |");
                System.out.println("\t\t|\t3. Modifier Les informatons d'un adherent  |");
                System.out.println("\t\t|\t4. Lister tous les Adhernts                |");
                System.out.println("\t\t|\t5. Rechercher un adherent                  |");
                System.out.println("\t\t|\t6. Exits                                   |");
                System.out.println("\t\t----------------------------------------------------");
                System.out.print("Quelle operation souhaitez-vous effectuer?: ");
                choice = in.nextInt();
                // menu(choice);
                break;
            case 1:
                formulaires(in);
                choice = 0;
                break;
            case 2:
                if (Adherent.getNbrAdherents() == 0) {
                    System.out.println("La liste est encore vide");
                } else {
                    System.out.println("Entrer le numero de la personne");
                    int numero = in.nextInt();
                    App.supAdherent(numero);
                }
                break;
            case 3:
                System.out.println("Entrer l'identifiant de l'utilisateur:");
                App.updateAdherent(in.nextInt());
                break;
            case 4:
                System.out.println("\f\t\t\t\tRecuperation de la liste...\n");
                App.listeUtilisateur();
                break;
            case 5:
                System.out.print("Entrer votre recherche: ");
                choice = App.listeUtilisateurFilter((new Scanner(System.in)).nextLine());
                break;
            case 6:
                System.out.println("Good Bye Boy!");
                return 0;
            default:
                // menu(x);
                break;
        }
        menu(choice);
        return 0;
    }

    public static void menuDocument(int x) { // Menue qui gere les documents
        // Scanner in = new Scanner(System.in);
        document = null;
        document = App.getAllDocuments();
        int choice = 0;
        switch (x) {
            case 0:
                System.out.println("\t\t----------------------------------------------------");
                System.out.println("\t\t|\t1. Ajouter Document                        |");
                System.out.println("\t\t|\t2. Supprimer Document                      |");
                System.out.println("\t\t|\t3. Modifier Les informatons d'un Document  |");
                System.out.println("\t\t|\t4. Lister tous les Documents               |");
                System.out.println("\t\t|\t5. Rechercher un Document                  |");
                System.out.println("\t\t|\t6. Verifier Disponibilite                  |");
                System.out.println("\t\t|\t8. Exits                                   |");
                System.out.println("\t\t----------------------------------------------------");
                System.out.print("Quelle operation souhaitez-vous effectuer?: ");
                choice = (new Scanner(System.in)).nextInt();
                break;
            case 1:
                System.out.println("\t\t----------------------------------------------------");
                System.out.println("\t\t|\t1. Livre                                   |");
                System.out.println("\t\t|\t2. Article                                 |");
                System.out.println("\t\t|\t3. Magazine                                |");
                System.out.println("\t\t|\t4. Memoire                                 |");
                System.out.println("\t\t|\t8. Exits                                   |");
                System.out.println("\t\t----------------------------------------------------");
                System.out.print("Quelle document souhaitez-vous ajouter?: ");
                buildDoc((new Scanner(System.in)).nextInt());
                break;
            case 2:
                System.out.print("Entrer le code du document a suprimmer: ");
                App.deleteDoc((new Scanner(System.in)).nextLine());
                break;
            case 4:
                System.out.println("\f\t\t\t\tRecuperation de la liste...\n");
                System.out.println(document.length);
                App.st = new ArrayList[5];
                for (int i = 0; i < document.length; i++) {
                    document[i].affiche();
                    System.out.println("");
                }
                break;
            case 6:
                System.out.print("Entrer le code du document: ");
                App.dispoDoc((new Scanner(System.in)).nextLine());
                break;
            case 8:
                return;
            default:
                break;
        }
        menuDocument(choice);
    }

    public static void buildDoc(int x) {// Option pour creer un nouveau document celon le type
        // System.out.println("top");
        Document document = new Document();
        switch (x) {
            case 1:
                document = Livre.insert();
                break;
            case 2:
                document = Article.insert();
                break;
            case 3:
                document = Magazine.insert();
                break;
            case 4:
                document = Memoire.insert();
                break;
            default:
                System.out.println("Choix incorrect.");
                break;
        }
        if (document != null) {
            App.insertDocoment(document);
        }
        return;
    }

    public static void formulaires(Scanner in) {// formulaire pour ajouter un adherent

        System.out.println("Entrer votre Nom: ");
        String nom = in.nextLine();
        System.out.println("Entrer votre Prenom: ");
        String prenom = in.nextLine();
        System.out.println("Entrer votre Adresse: ");
        String adresse = in.nextLine();
        int choix = 0;
        String type = "";
        try {
            choix = statut(in);
        } catch (Exception e) {
            System.out.println(
                    "Alert: Si vous faite encore une erreur la procedure seras annuler et vos informations serons perdus");
            choix = statut(in);
        }
        switch (choix) {
            case 1:
                // listeAdherent.add();
                type = "etudiant";
                break;
            case 2:
                type = "enseignant";
                break;
            default:
                type = "autre";
                break;
        }
        App.insererAdherent(new Adherent(Adherent.nbr + 1, nom, prenom, adresse, type));
        System.out.println("Nouvel adherent ajouter");
    }

    public static int menuEmprunt(int x) { // Menue emprunt
        Scanner in = new Scanner(System.in);
        int choice = 0;
        switch (x) {
            case 0:
                System.out.println("\t\t----------------------------------------------------");
                System.out.println("\t\t|\t1. Emprunter un livre                      |");
                System.out.println("\t\t|\t2. rendre un livre                         |");
                System.out.println("\t\t|\t3. Lister tous les Emprunts                |");
                System.out.println("\t\t|\t4. Exits                                   |");
                System.out.println("\t\t----------------------------------------------------");
                System.out.print("Quelle operation souhaitez-vous effectuer?: ");
                choice = in.nextInt();
                break;
            case 1:
                if (Adherent.getNbrAdherents() == 0) {
                    System.out.println("La liste est encore vide");
                } else {
                    choice = App.reserverDoc(in);
                }
                break;
            case 2:
                choice = App.rendreDoc(in);
                break;
            case 3:
                System.out.println("\f\t\t\t\tRecuperation de la liste...\n");
                App.getAllEmprunt("all");
                break;
            case 4:
                System.out.println("Good Bye Boy!");
                return 0;
            default:
                break;
        }
        menuEmprunt(choice);
        return 0;
    }

    public static int statut(Scanner in) {
        System.out.println("Entrer le statut de l'adherent: \n\t1. Etudiant\n\t2. Enseignants\n\t3. Autres");
        int choice = in.nextInt();
        return choice;
    }

    public static void begin(int x) {
        Scanner in = new Scanner(System.in);
        int choice = 0;

        switch (x) {
            case 0:
                System.out.println("\f\t\t\t\tWelcome On SmartBibli");
                System.out.println("\t\t------------------------------------------------------");
                System.out.println("\t\t|\t\t1. Gestion des Adherents             |");
                System.out.println("\t\t|\t\t2. Gestion des Documents             |");
                System.out.println("\t\t|\t\t3. Gestion des Emprunts              |");
                System.out.println("\t\t|\t\t4. Exit                              |");
                System.out.println("\t\t------------------------------------------------------");
                System.out.print("Quelle operation souhaitez-vous effectuer?: ");
                choice = in.nextInt();
                break;
            case 1:
                choice = menu(0);
                break;
            case 2:
                menuDocument(0);
                break;
            case 3:
                menuEmprunt(0);
                break;
            case 4:
                return;
            default:
                begin(choice);
                break;
        }
        begin(choice);
    }

    public static void main(String[] args) {
        App.connectbd();

        document = App.getAllDocuments();
        App.setNombreAdherent();
        App.check();
        try {
            begin(0);
        } catch (InputMismatchException e) {
            System.out.println("Vous avez entrer un caractere(ou chaine de caracter) au lieun d'un nombre");
            begin(0);
        } catch (NoSuchElementException e) {
            System.out.println("\nGood Bye!");
            // e.printStackTrace();
        }
    }
}
