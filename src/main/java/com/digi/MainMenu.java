package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.*;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        GestionRessources.setRessources(db.getCollection("Ressources"));
        GestionCitoyens.setCitoyens(db.getCollection("Citoyens"));
        GestionBatiments.setBatiments(db.getCollection("Batiments"));

        Scanner scanner = new Scanner(System.in);
        int choiceMenu;

        do {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Gérer les Ressources");
            System.out.println("2. Gérer les Citoyens");
            System.out.println("3. Gérer les Bâtiments");
            System.out.println("4. Gérer les Missions");
            System.out.println("5. Quitter l'application");
            System.out.print("Veuillez choisir une option : ");

            choiceMenu = scanner.nextInt();

            switch (choiceMenu) {
                case 1:
                    menuRessources(scanner);
                    break;
                case 2:
                    menuCitoyens(scanner);
                    break;
                case 3:
                    menuBatiments(scanner);
                    break;
                case 4:
                    menuMissions(scanner);
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choiceMenu != 5);

        scanner.close();
    }

    private static void menuRessources(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n===== Menu Ressources =====");
            System.out.println("1. Afficher les Ressources");
            System.out.println("2. Ajouter une Ressource");
            System.out.println("3. Mettre à jour une Ressource");
            System.out.println("4. Supprimer une Ressource");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    GestionRessources.afficherRessources();
                    break;
                case 2:
                    System.out.print("Type de ressource : ");
                    String typeAjout = scanner.next();
                    System.out.print("Quantité : ");
                    int quantiteAjout = scanner.nextInt();
                    GestionRessources.ajouterRessources(typeAjout, quantiteAjout);
                    break;
                case 3:
                    System.out.print("Type de ressource : ");
                    String typeMaj = scanner.next();
                    System.out.print("Nouvelle quantité : ");
                    int nouvelleQuantite = scanner.nextInt();
                    GestionRessources.mettreAJourQuantiteRessources(typeMaj, nouvelleQuantite);
                    break;
                case 4:
                    System.out.print("Type de ressource à supprimer : ");
                    String typeSupprime = scanner.next();
                    GestionRessources.supprimeRessources(typeSupprime);
                    break;
                case 5:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (choice != 5);
    }

    private static void menuCitoyens(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n===== Menu Citoyens =====");
            System.out.println("1. Afficher les Citoyens");
            System.out.println("2. Ajouter un type de Citoyens");
            System.out.println("3. Mettre à jour quantité d'un type de Citoyens");
            System.out.println("4. Mettre à jour le rôle d'un type de Citoyens");
            System.out.println("5. Supprimer un type de Citoyens");
            System.out.println("6. Retour au menu principal");
            System.out.print("Votre choix : ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    GestionCitoyens.afficherCitoyens();
                    break;
                case 2:
                    System.out.print("Nom : ");
                    String nomAjout = scanner.next();
                    System.out.print("Quantité : ");
                    int quantiteAjout = scanner.nextInt();
                    System.out.print("Rôle : ");
                    String roleAjout = scanner.next();
                    GestionCitoyens.ajouterCitoyens(nomAjout, quantiteAjout, roleAjout);
                    break;
                case 3:
                    System.out.print("Nom : ");
                    String nomMaj = scanner.next();
                    System.out.print("Quantité à ajouter ou retirer : ");
                    int changementQuantite = scanner.nextInt();
                    System.out.print("Rôle : ");
                    String roleMaj = scanner.next();
                    GestionCitoyens.mettreAJourQuantiteCitoyens(nomMaj, changementQuantite, roleMaj);
                    break;
                case 4:
                    System.out.print("Nom : ");
                    String nomRoleMaj = scanner.next();
                    System.out.print("Ancien rôle : ");
                    String ancienRole = scanner.next();
                    System.out.print("Nouveau rôle : ");
                    String nouveauRole = scanner.next();
                    System.out.print("Quantité à transférer : ");
                    int qteSwitch = scanner.nextInt();
                    GestionCitoyens.mettreAJourRoleCitoyens(nomRoleMaj, ancienRole, nouveauRole, qteSwitch);
                    break;
                case 5:
                    System.out.print("Nom : ");
                    String nomSupprime = scanner.next();
                    System.out.print("Rôle : ");
                    String roleSupprime = scanner.next();
                    GestionCitoyens.supprimeCitoyens(nomSupprime, roleSupprime);
                    break;
                case 6:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (choice != 6);
    }

    private static void menuBatiments(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n===== Menu Bâtiments =====");
            System.out.println("1. Afficher les Bâtiments");
            System.out.println("2. Construire un Bâtiment");
            System.out.println("3. Améliorer un Bâtiment");
            System.out.println("4. Supprimer un Bâtiment");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    GestionBatiments.afficherBatiments();
                    break;
                case 2:
                    System.out.print("Type de bâtiment : ");
                    String typeConstruction = scanner.next();
                    System.out.print("Fonction : ");
                    String fonctionConstruction = scanner.next();
                    System.out.print("Coût en bois : ");
                    int coutBoisConstruction = scanner.nextInt();
                    System.out.print("Coût en pierre : ");
                    int coutPierreConstruction = scanner.nextInt();
                    GestionBatiments.construire1NiveauBatiment(typeConstruction, coutBoisConstruction, coutPierreConstruction, fonctionConstruction);
                    break;
                case 3:
                    System.out.print("Type : ");
                    String typeAmelioration = scanner.next();
                    System.out.print("Niveau à ajouter : ");
                    int niveauAjouter = scanner.nextInt();
                    System.out.print("Coût en bois par niveau : ");
                    int coutBois = scanner.nextInt();
                    System.out.print("Coût en pierre par niveau : ");
                    int coutPierre = scanner.nextInt();
                    System.out.print("Fonction : ");
                    String fonctionAmelioration = scanner.next();
                    GestionBatiments.ameliorerBatiment(typeAmelioration, niveauAjouter, coutBois, coutPierre, fonctionAmelioration);
                    break;
                case 4:
                    System.out.print("Type : ");
                    String typeSupprime = scanner.next();
                    System.out.print("Fonction : ");
                    String fonctionSupprime = scanner.next();
                    GestionBatiments.supprimeBatiment(typeSupprime, fonctionSupprime);
                    break;
                case 5:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (choice != 5);
    }

    private static void menuMissions(Scanner scanner) {
        System.out.println("\n===== Menu Missions =====");
        System.out.print("Nom de la mission : ");
        String nomMission = scanner.next();
        System.out.print("Soldats nécessaires : ");
        int soldatsNecessaires = scanner.nextInt();
        System.out.print("Bois nécessaires : ");
        int boisNecessaire = scanner.nextInt();
        System.out.print("Nourriture nécessaire : ");
        int nourritureNecessaire = scanner.nextInt();

        if (GestionMission.preparerMission(nomMission, soldatsNecessaires, boisNecessaire, nourritureNecessaire)) {
            System.out.println("Voulez-vous envoyer la mission ? (y/n)");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("y")) {
                GestionMission.envoyerMission(nomMission, soldatsNecessaires, boisNecessaire, nourritureNecessaire);
                GestionMission.caclulMission(nomMission, soldatsNecessaires, boisNecessaire, nourritureNecessaire);
            } else {
                System.out.println("Mission annulée.");
            }
        } else {
            System.out.println("Préparation impossible, ressources insuffisantes.");
        }
    }

}

