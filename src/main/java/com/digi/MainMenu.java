package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionRessources;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();


        System.out.println("Menu :");
        System.out.println("1. Modifier Ressources");
        System.out.println("2. Modifier Citoyens");
        System.out.println("3. Fin de l’application");
        System.out.print("Veuillez choisir une option : ");

        //Creation du scanner "Menu" utilisé pour taper l'option que l'utilisateur veut utiliser
        Scanner scannerMenu = new Scanner(System.in);
        //Variable qui servira au stockage de la variable entrée par le scanner "Menu"
        int choice = -1;

        while (choice < 1 || choice > 3) {
            //Check si l'input est un intègre
            if (!scannerMenu.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.");
                scannerMenu.next();
                continue;
            }

            choice = scannerMenu.nextInt();

            //Check si l'input est compris entre 1 et 3
            if (choice < 1 || choice > 3) {
                System.out.println("ERREUR ! Vous venez d'entrée un nombre entier autre que les nombre de 1 à 3.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.*");
            }

        }

        //switch pour assigner le choix entré par l'utilisateur à une option affiché précédement
        switch (choice) {
            case 1:
                System.out.println();
                GestionRessources.setRessources(db.getCollection("Ressources"));
                affichageMenuRessources();
                break;
            case 2:
                System.out.println();
                GestionRessources.setRessources(db.getCollection("Citoyens"));
                affichageMenuCitoyens();
                break;
            case 3:
                System.out.println("Au revoir !");
                break;
        }
        //Fermeture scanner ci-dessus
        scannerMenu.close();
    }

    private static void affichageMenuRessources() {
        System.out.println("1. Affichage de la filmographie d’un acteur donné");
        System.out.println("2. Affichage du casting d’un film donné");
        System.out.println("3. Fin de l’application");
        System.out.print("Veuillez choisir une option : ");

        Scanner scannerMenuRessources = new Scanner(System.in);
        //Variable qui servira au stockage de la variable entrée par le scanner "Menu"
        int choice = -1;

        while (choice < 1 || choice > 3) {
            //Check si l'input est un intègre
            if (!scannerMenuRessources.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.");
                scannerMenuRessources.next();
                continue;
            }

            choice = scannerMenuRessources.nextInt();

            //Check si l'input est compris entre 1 et 3
            if (choice < 1 || choice > 3) {
                System.out.println("ERREUR ! Vous venez d'entrée un nombre entier autre que les nombre de 1 à 3.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.*");
            }

        }

        //switch pour assigner le choix entré par l'utilisateur à une option affiché précédement
        switch (choice) {
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println("Au revoir !");
                break;
        }
        //Fermeture scanner ci-dessus
        scannerMenuRessources.close();
    }

    private static void affichageMenuCitoyens() {
        System.out.println("1. Affichage de la filmographie d’un acteur donné");
        System.out.println("2. Affichage du casting d’un film donné");
        System.out.println("3. Fin de l’application");
        System.out.print("Veuillez choisir une option : ");

        Scanner scannerMenuCitoyens = new Scanner(System.in);
        //Variable qui servira au stockage de la variable entrée par le scanner "Menu"
        int choice = -1;

        while (choice < 1 || choice > 3) {
            //Check si l'input est un intègre
            if (!scannerMenuCitoyens.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.");
                scannerMenuCitoyens.next();
                continue;
            }

            choice = scannerMenuCitoyens.nextInt();

            //Check si l'input est compris entre 1 et 3
            if (choice < 1 || choice > 3) {
                System.out.println("ERREUR ! Vous venez d'entrée un nombre entier autre que les nombre de 1 à 3.\nVeuillez entrée un nombre entre 1 et 2 comme option, ou le nombre 3 pour quittez le programme.*");
            }

        }

        //switch pour assigner le choix entré par l'utilisateur à une option affiché précédement
        switch (choice) {
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println("Au revoir !");
                break;
        }
        //Fermeture scanner ci-dessus
        scannerMenuCitoyens.close();
    }
}
