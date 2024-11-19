package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionBatiments;
import com.digi.div.GestionRessources;
import com.mongodb.client.MongoDatabase;

public class MainTestBatiments {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        GestionBatiments.setBatiments(db.getCollection("Batiments"));
        GestionRessources.setRessources(db.getCollection("Ressources"));

        GestionBatiments.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionRessources.ajouterRessources("Bois", 10);
        GestionRessources.ajouterRessources("Pierre", 20);

        GestionBatiments.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiments.construire1NiveauBatiment("Tour", 100,200, "Test 1");

        GestionBatiments.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiments.ameliorerBatiment("Tour", 2,10,20,"Test 1");

        GestionBatiments.afficherBatiments();
        GestionRessources.afficherRessources();

        GestionBatiments.supprimeBatiment("Tour", "Test 1");

        GestionBatiments.afficherBatiments();
        GestionRessources.afficherRessources();

        //Fermeture de la base de données
        connexion.fermerConnexion();
    }
}
