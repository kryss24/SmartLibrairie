import java.sql.Statement;

import documents.*;
import utilisateurs.Adherent;
import utilisateurs.Emprunt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;

public class App {
    public static Connection conn = null;

    // public static int nbr = 0;
    public static void connectbd() {
        // Informations de connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/biblismart";
        String username = "root";
        String password = "";
        // Établir la connexion à la base de données
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Document[] getAllDocuments() {
        Document[] documents = null;

        try {
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(idDoc) as nbr FROM document");
            ResultSet rs2;
            int nbr = 0;
            if (rs.next()) {
                nbr = Integer.parseInt(rs.getString("nbr"));
            }
            int i = 0;
            documents = new Document[nbr];
            rs = stmt.executeQuery("SELECT * FROM document");
            while (rs.next()) {
                if (rs.getString("type").equals("livre")) {
                    rs2 = stmt2.executeQuery(
                            "SELECT * FROM livre WHERE idLivre =" + Integer.parseInt(rs.getString("idD")));
                    if (rs2.next()) {
                        documents[i] = new Livre(rs.getString("title"),
                                new int[] { Integer.parseInt(rs.getString("salle")),
                                        Integer.parseInt(rs.getString("rayon")) },
                                Integer.parseInt(rs.getString("nombre")), rs2.getString("nomAuteur"),
                                rs2.getString("datePublication"), rs2.getString("nomEditeur"));
                    }
                } else if (rs.getString("type").equals("magazine")) {
                    rs2 = stmt2.executeQuery(
                            "SELECT * FROM magazine WHERE idMagazine =" + Integer.parseInt(rs.getString("idD")));
                    if (rs2.next()) {
                        documents[i] = new Magazine(rs.getString("title"),
                                new int[] { Integer.parseInt(rs.getString("salle")),
                                        Integer.parseInt(rs.getString("rayon")) },
                                Integer.parseInt(rs.getString("nombre")), Integer.parseInt(rs2.getString("frequency")));
                    }
                } else if (rs.getString("type").equals("memoire")) {
                    rs2 = stmt2.executeQuery(
                            "SELECT * FROM memoire WHERE idMemoire =" + Integer.parseInt(rs.getString("idD")));
                    if (rs2.next()) {
                        documents[i] = new Memoire(rs.getString("title"),
                                new int[] { Integer.parseInt(rs.getString("salle")),
                                        Integer.parseInt(rs.getString("rayon")) },
                                Integer.parseInt(rs.getString("nombre")), rs2.getString("nomCandidat"),
                                rs2.getString("dateSoutenance"));
                    }
                } else if (rs.getString("type").equals("article")) {
                    rs2 = stmt2.executeQuery(
                            "SELECT * FROM article WHERE idArticle =" + Integer.parseInt(rs.getString("idD")));
                    if (rs2.next()) {
                        documents[i] = new Article(rs.getString("title"),
                                new int[] { Integer.parseInt(rs.getString("salle")),
                                        Integer.parseInt(rs.getString("rayon")) },
                                Integer.parseInt(rs.getString("nombre")), rs2.getString("nomAuteur"),
                                rs2.getString("datePublication"));
                    }
                }
                i++;
            }
            rs2 = stmt2.executeQuery("SELECT MAX(idDoc) FROM document");
            if (rs2.next()) {
                Document.setNbrDocument(Integer.parseInt(rs2.getString("MAX(idDoc)")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documents;
    }

    public static void listeUtilisateur() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM adherent where etat = 0");
            Adherent adherentl = null;
            while (rs.next()) {
                adherentl = new Adherent(Integer.parseInt(rs.getString("id")), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("adresse"), rs.getString("type"));
                adherentl.affiche();
                System.err.println("");
            }
            setNombreAdherent();
            if (adherentl == null) {
                System.err.println("Aucun adherent dans la Bd!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setNombreAdherent() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(id), MAX(id) FROM adherent");
            if (rs.next()) {
                Adherent.setNbrAdherents(Integer.parseInt(rs.getString("COUNT(id)")));
                Adherent.nbr = Integer.parseInt(rs.getString("MAX(id)"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void insererAdherent(Adherent adherent) {
        try {
            String query = "INSERT INTO adherent values(?,?,?,?,?,0)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, "" + adherent.getNom());
            preparedStatement.setString(3, "" + adherent.getPrenom());
            preparedStatement.setString(4, "" + adherent.getAdresse());
            preparedStatement.setString(5, "" + adherent.getType());
            preparedStatement.execute();
            setNombreAdherent();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("erreur lors de l'ajout. Error:" + e.getMessage());
        }
    }

    public static void supAdherent(int info) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM adherent where id = " + info);
            if (rs.next()) {
                String query = "UPDATE adherent SET etat = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, info);
                preparedStatement.execute();
                setNombreAdherent();
            } else {
                System.out.println("Good Job");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("erreur lors de l'ajout. Error:" + e.getMessage());
        }
    }

    public static Adherent searchAdh(int id) {
        Adherent adh = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM adherent WHERE id = " + id + " AND etat = 0");
            Emprunt emprunt = null;
            if (rs.next()) {
                adh = new Adherent(Integer.parseInt(rs.getString("id")), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("adresse"), rs.getString("type"));
                rs = stmt.executeQuery("SELECT * FROM reservatins WHERE idAdh = " + id);
                adh.affiche();
                while (rs.next()) {
                    emprunt = new Emprunt(id, rs.getString("codeDoc"), rs.getDate("dateEmprunt"),
                            rs.getDate("dateDeliv"), rs.getInt("etat"));
                    // adh.setEmpruntActuel(Integer.parseInt(rs.getString("COUNT(idRes)")));
                    adh.enprunt(emprunt);
                    System.err.println("probleme");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return adh;
    }

    public static boolean searchDoc(String code) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM document WHERE code = '" + code + "' OR title = '" + code
                    + "' OR type = '" + code + "'");
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public static int reserverDoc(Scanner in) {
        System.out.print("Entrer le numero de l'adherent (0 pour quitter) : ");
        int id = in.nextInt();
        if (id != 0) {
            Adherent adh;
            if ((adh = searchAdh(id)) != null) {
                System.out.println("L'adherent a exactement " + adh.getEmpruntActuel() + " sur " + adh.nbrEmpruntable()
                        + " emprunt en cour:");
                adh.afficheEmprunt();
                if (adh.getEmpruntActuel() < adh.nbrEmpruntable() && adh.retard() == false) {
                    System.out.println(
                            "Entrer le code, le titre ou le type(livre, magazine, article, memoire) du document");
                    String code = (new Scanner(System.in)).nextLine();
                    code = code.toLowerCase();
                    if (searchDoc(code)) {
                        try {
                            String query = "INSERT INTO reservatins VALUES(0,?,?,?,?,0)";
                            PreparedStatement preparedStatement = conn.prepareStatement(query);
                            preparedStatement.setInt(1, adh.getIdentifiant());
                            preparedStatement.setString(2, code);
                            preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                            preparedStatement.setDate(4,
                                    java.sql.Date.valueOf((LocalDate.now()).plusDays(adh.getDelai())));
                            preparedStatement.execute();
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Le document n'existe pas");
                    }
                } else {
                    System.out.println("Emprunt Impossible.");
                }

            } else {
                System.out.println("L'adherent n'existe pas");
            }
            return 4;
        }
        return 0;
    }

    public static Emprunt searchEmp(int id) {
        Emprunt emp = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservatins WHERE idRes = " + id);
            if (rs.next()) {
                emp = new Emprunt(rs.getInt("idAdh"), rs.getString("codeDoc"), rs.getDate("dateEmprunt"),
                        rs.getDate("dateDeliv"), rs.getInt("etat"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return emp;
    }

    public static int rendreDoc(Scanner in) {
        System.out.print("Entrer l'identifiant de l'emprunt (0 pour quitter) : ");
        int id = in.nextInt();

        if (id != 0) {
            Emprunt emp;
            if ((emp = searchEmp(id)) != null) {
                System.out.println(emp.toString());
                System.out.println(" Es ce cette reservation que vous voullez effectuer (0 pour non et 1 pour oui) ?");
                try {
                    int choix = (new Scanner(System.in)).nextInt();
                    if (choix == 1) {

                        String query = "UPDATE reservatins SET etat = ? WHERE idRes = " + id;
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, 1);
                        preparedStatement.execute();

                    } else {
                        return 5;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("Une erreur c'est produite");
                }
            } else {
                System.out.println("L'emprunt n'existe pas");
            }
            return 5;
        }
        return 0;

    }

    public static void updateAdherent(int adId) {
        try {
            Adherent adh = null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM adherent WHERE id = " + adId);
            if (rs.next()) {
                // adh = new Adherent(Integer.parseInt(rs.getString("id")), ,,
                // , rs.getString("type"));
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String type = rs.getString("type");
                Scanner in = new Scanner(System.in);
                // rs = stmt.executeQuery("SELECT * FROM reservatins WHERE idAdh = " + id);
                // adh.affiche();
                System.out.println("voulez vous chager le nom? :" + nom
                        + "\n(oui/ toute valeur autre que oui seras conciderer comme non)");
                if (in.nextLine().equals("oui")) {
                    System.out.print("Entrer le nouveau nom: ");
                    nom = in.nextLine();
                }
                System.out.println("voulez vous chager le prenom? :" + prenom
                        + "\n(oui/ toute valeur autre que oui seras conciderer comme non)");
                if (in.nextLine().equals("oui")) {
                    System.out.print("Entrer le nouveau prenom: ");
                    prenom = in.nextLine();
                }
                System.out.println("voulez vous chager l'adresse? :" + adresse
                        + "\n(oui/ toute valeur autre que oui seras conciderer comme non)");
                if (in.nextLine().equals("oui")) {
                    System.out.print("Entrer la nouvelle adresse: ");
                    adresse = in.nextLine();
                }
                System.out.println("voulez vous chager le type de l'adherent? :" + type
                        + "\n(oui/ toute valeur autre que oui seras conciderer comme non)");
                if (in.nextLine().equals("oui")) {
                    System.out.print("Entrer : \n1)Etudiant\n2)Enseignant\n3)Autres\n");
                    switch (in.nextLine()) {
                        case "1":
                            type = "etudiant";
                            break;
                        case "2":
                            type = "enseignant";
                            break;
                        case "3":
                            type = "autre";
                            break;
                        default:
                            System.err.println("Error, the choice should be 1, 2 or 3");
                            break;
                    }
                }
                System.out.println(nom + " " + prenom + " " + adresse + " " + type);
                String query = "UPDATE adherent SET nom = ?, prenom = ?, adresse = ?, type = ? WHERE idRes = " + adId;
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, adresse);
                preparedStatement.setString(4, type);
                preparedStatement.execute();
            } else {
                System.err.println("L'adherent n'existe pas!");
            }
            // PreparedStatement preparedStatement = conn.prepareStatement(query);
            // preparedStatement.setInt(1, 1);
            // preparedStatement.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void check() {
        try {
            Emprunt emprunt = null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservatins");

            while (rs.next()) {
                emprunt = new Emprunt(rs.getInt("idAdh"), rs.getString("codeDoc"), rs.getDate("dateEmprunt"),
                        rs.getDate("dateDeliv"), rs.getInt("etat"));
                if (emprunt.dateCompaire()) {
                    String query = "UPDATE reservatins SET etat = ? WHERE idRes = " + rs.getInt("idRes");
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, 2);
                    preparedStatement.execute();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}