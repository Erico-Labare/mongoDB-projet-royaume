package com.digi.div;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class GestionRessources {

    private static MongoCollection<Document> ressources;

    public GestionRessources(MongoCollection<Document> ressources) {
        this.ressources = ressources;
    }

    public static void ajouterRessources(String type, int quantite) {
        Document ressource = ressources.find(new Document("type", type)).first();
        if (ressource != null) {
            int nouvelleQuantite = quantite + ressource.getInteger("quantite", 0);
            mettreAJourQuantiteRessources(type, nouvelleQuantite);
        } else {
            ressources.insertOne(new Document("type", type).append("quantite", quantite));
        }
        System.out.println("Ressource ajoutée : "+ quantite + " " + type);
    }

    public static void afficherRessources() {
        for (Document ressources : ressources.find()) {
            System.out.println(ressources.toJson());
        }
    }

    public static void mettreAJourQuantiteRessources(String type, int nouvelleQuantite){
        ressources.updateOne(eq("type", type), set("quantite", nouvelleQuantite));
        System.out.println("Quantité mis à jour pour : " +type);
    }

    public static void supprimeRessources(String type) {
        ressources.deleteOne(new Document("type", type));
        System.out.println("Ressource supprimée : " +type);
    }

    public static boolean verifierQantiteRessources(String type, int nombreAttendu) {
        boolean check = false;

        Document ressource = ressources.find(eq("type", type)).first();

        if (ressource != null) {
            long quantite = ressource.getInteger("quantite");
            if (quantite >= nombreAttendu) {
                check = true;
            }
        }
        return check;
    }

    /** Setter
     *@param ressources collection
     */
    public static void setRessources(MongoCollection<Document> ressources) {
        GestionRessources.ressources = ressources;
    }
}
