import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;

import documents.*;
import utilisateurs.*;

public class Main {
    private static Document[] document = new Document[60];

    private static int menu(int x) {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        switch (x) {
            case 0:
                System.out.println("\t\t----------------------------------------------------");
                System.out.println("\t\t|\t1. Ajouter Adherent                        |");
                System.out.println("\t\t|\t2. Supprimer Adherent                      |");
                System.out.println("\t\t|\t3. Modifier Les informatons d'un adherent  |");
                System.out.println("\t\t|\t4. Emprunter un livre                      |");
                System.out.println("\t\t|\t5. rendre un livre                         |");
                System.out.println("\t\t|\t6. Lister tous les livres                  |");
                System.out.println("\t\t|\t7. Lister tous les Adhernts                |");
                System.out.println("\t\t|\t8. Exits                                   |");
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
                if (Adherent.getNbrAdherents() == 0) {
                    System.out.println("La liste est encore vide");
                } else {
                    choice = App.reserverDoc(in);
                }
                break;
            case 5:
                choice = App.rendreDoc(in);
                break;
            case 6:
                System.out.println("\f\t\t\t\tRecuperation de la liste...\n");
                for (int i = 0; i < document.length; i++) {
                    document[i].affiche();
                    System.out.println("");
                }
                break;
            case 7:
            System.out.println("\f\t\t\t\tRecuperation de la liste...\n");
                App.listeUtilisateur();
                break;
            case 8:
                System.out.println("Good Bye Boy!");
                in.close();
                return 0;
            default:
                menu(x);
                break;
        }
        menu(choice);
        return 0;
    }

    public static void formulaires(Scanner in) {
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

    public static int statut(Scanner in) {
        System.out.println("Entrer le statut de l'adherent: \n\t1. Etudiant\n\t2. Enseignants\n\t3. Autres");
        int choice = in.nextInt();
        return choice;
    }

    public static void main(String[] args) {
        App.connectbd();

        document = App.getAllDocuments();
        App.setNombreAdherent();
        App.check();
        System.out.println("\f\t\t\t\tWelcome On SmartBibli");
        try {
            menu(0);
        } catch (InputMismatchException e) {
            System.out.println("Vous avez entrer un caractere(ou chaine de caracter) au lieun d'un nombre");
            menu(0);
        } catch (NoSuchElementException e) {
            System.out.println("\nGood Bye!");
            // e.printStackTrace();
        }
    }
}
