package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionBatiment;
import com.digi.div.GestionRessources;
import com.mongodb.client.MongoDatabase;

public class MainTestBatiments {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        GestionBatiment.setBatiments(db.getCollection("Batiments"));
        GestionRessources.setRessources(db.getCollection("Ressources"));

        GestionBatiment.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionRessources.ajouterRessources("Bois", 10);
        GestionRessources.ajouterRessources("Pierre", 20);

        GestionBatiment.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiment.construire1NiveauBatiment("Tour", 100,200, "Test 1");

        GestionBatiment.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiment.ameliorerBatiment("Tour", 2,10,20,"Test 1");

        GestionBatiment.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiment.supprimeBatiment("Tour", "Test 1");

        GestionBatiment.afficherBatiments();
        GestionRessources.afficherRessources();

        //Fermeture de la base de données
        connexion.fermerConnexion();
    }
}
