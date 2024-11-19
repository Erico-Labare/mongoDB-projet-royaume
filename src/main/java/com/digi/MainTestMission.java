package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionCitoyens;
import com.digi.div.GestionMission;
import com.digi.div.GestionRessources;
import com.mongodb.client.MongoDatabase;

public class MainTestMission {
    public static void main(String[] args) {

        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        GestionCitoyens.setCitoyens(db.getCollection("Citoyens"));
        GestionRessources.setRessources(db.getCollection("Ressources"));

        GestionCitoyens.afficherCitoyens();
        GestionRessources.afficherRessources();
        System.out.println("\n");

        GestionCitoyens.ajouterCitoyens("Soldat", 100, "attaquant");

        GestionCitoyens.afficherCitoyens();
        GestionRessources.afficherRessources();
        System.out.println("\n");

        GestionMission.preparerMission("Mission 1", 10, 100, 200);

        connexion.fermerConnexion();
    }
}