package com.digi.div;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongoDB {
    public static void main(String[] args) {

        ConnexionMongoDB connexion = new ConnexionMongoDB();

        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        connexion.fermerConnexion();
    }

    private MongoDatabase database;
    private MongoClient mongoClient;

    public void ouvrirConnexion(String lien, String nom) {
        try {
            mongoClient = MongoClients.create(lien);
            database = mongoClient.getDatabase(nom);
            System.out.println("Connexion réussie à : " + database.getName());
        } catch (Exception e) {
            System.err.println("Erreur pendant la connexion :");
            e.printStackTrace();
        }
    }

    public void fermerConnexion() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Connexion fermée");
            } catch (Exception e) {
                System.err.println("Erreur pendant la fermeture de la connexion :");
                e.printStackTrace();
            }
        }
    }

    public MongoDatabase getDB() {
        return database;
    }


}
