package com.digi;

import com.digi.div.ConnexionMongoDB;
import com.digi.div.GestionCitoyens;
import com.digi.div.GestionRessources;
import com.mongodb.client.MongoDatabase;

public class MainTestRessources {
    public static void main(String[] args) {
        ConnexionMongoDB connexion = new ConnexionMongoDB();

        //Connexion à la base données
        connexion.ouvrirConnexion("mongodb://localhost:27017", "Royaume");

        MongoDatabase db = connexion.getDB();

        //Choisir la collection à éditer
        GestionRessources.setRessources(db.getCollection("Ressources"));

        //Ajouter la ressource "Fer"
        GestionRessources.ajouterRessources("Or", 500);

        //Afficher toutes les collection
        GestionRessources.afficherRessources();

        //Changer la quantite de Bois
        GestionRessources.mettreAJourQuantiteRessources("Or", 700);

        //RE-afficher toutes les collection
        GestionRessources.afficherRessources();


        if (GestionRessources.verifierQantiteRessources("Or", 700)){
            System.out.println("Verif ok");
        } else {
            System.out.println("Verif pas ok");
        }

        GestionRessources.afficherRessources();

        //Supprimer la ressource "Fer"
        GestionRessources.supprimeRessources("Or");

        //RE-RE-afficher toutes les collection
        GestionRessources.afficherRessources();

        //Fermeture de la base de données
        connexion.fermerConnexion();
    }
}
