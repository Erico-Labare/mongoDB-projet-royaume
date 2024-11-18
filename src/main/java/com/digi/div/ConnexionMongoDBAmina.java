package com.digi.div;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Methode de connexion MongoDB d'après  Amina
 */
public class ConnexionMongoDBAmina {
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructeur qui établit la connexion
    public ConnexionMongoDBAmina() {
        // Connexion au serveur MongoDB local
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Accès à la base de données 'Royaume'
        database = mongoClient.getDatabase("Royaume");
        System.out.println("Connexion réussie à la base de données : " + database.getName());
    }

    // Méthode pour obtenir la base de données
    public MongoDatabase getDatabase() {
        return database;
    }

    // Méthode pour fermer la connexion
    public void fermerConnexion() {
        mongoClient.close();
        System.out.println("Connexion fermée.");
    }
}
