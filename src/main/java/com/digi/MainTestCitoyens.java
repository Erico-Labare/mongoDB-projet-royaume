package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionCitoyens;
import com.mongodb.client.MongoDatabase;

public class MainTestCitoyens {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        GestionCitoyens.setCitoyens(db.getCollection("Citoyens"));

        GestionCitoyens.ajouterCitoyens("Tester", 10, "Test 1");

        GestionCitoyens.afficherCitoyens();

        GestionCitoyens.mettreAJourQuantiteCitoyens("Tester", 20,"Test 1");

        GestionCitoyens.afficherCitoyens();

        GestionCitoyens.mettreAJourRoleCitoyens("Tester", "Test 1", "Test 2", 29);

        GestionCitoyens.afficherCitoyens();

        if (GestionCitoyens.verifierQantiteCitoyens("Tester", "Test 2", 20)) {
            System.out.println("Verif ok");
        } else {
            System.out.println("Verif pas ok");
        }

        GestionCitoyens.afficherCitoyens();

        GestionCitoyens.supprimeCitoyens("Tester", "Test 2");

        GestionCitoyens.afficherCitoyens();

        //Fermeture de la base de données
        connexion.fermerConnexion();
    }
}
