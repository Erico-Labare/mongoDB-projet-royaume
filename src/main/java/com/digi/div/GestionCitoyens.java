package com.digi.div;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class GestionCitoyens {

    private static MongoCollection<Document> citoyens;

    public GestionCitoyens(MongoCollection<Document> citoyens) {
        this.citoyens = citoyens;
    }

    public static void ajouterCitoyens(String nom, String role, int quantite) {
        Document citoyen = citoyens.find(new Document("nom", nom).append("role", role)).first();
        if (citoyen != null) {
            int nouvelleQuantite = quantite + citoyen.getInteger("quantite", 0);
            mettreAJourQuantiteCitoyens(nom, role, nouvelleQuantite);
        } else {
            citoyens.insertOne(new Document("nom", nom).append("quantite", quantite).append("role", role));
        }
        System.out.println("Ressource ajoutée : " + quantite + " " + nom);
    }

    public static void afficherCitoyens() {
        for (Document ressources : citoyens.find()) {
            System.out.println(ressources.toJson());
        }
    }

    public static void mettreAJourQuantiteCitoyens(String nom, String role, int nouvelleQuantite) {
        citoyens.updateOne(and(eq("nom", nom), eq("role", role)), set("quantite", nouvelleQuantite));
        System.out.println("Quantité mis à jour pour : " + nom + " " + role);
    }

    public static void mettreAJourRoleCitoyens(String nom, String ancienRole, String nouveauRole) {
        citoyens.updateOne(and(eq("nom", nom), eq("role", ancienRole)), set("role", nouveauRole));
        System.out.println("Role mis à jour pour :" + nom + " " + ancienRole + " vers " + nom + " " + nouveauRole);
    }


    public static boolean verifierQantiteCitoyens(String nom, String role, int nombreAttendu) {
        boolean check = false;

        Document citoyen = citoyens.find(and(eq("nom", nom), eq("role", role))).first();

        if (citoyen != null) {
            long quantite = citoyen.getInteger("quantite");
            if (quantite >= nombreAttendu) {
                check = true;
            }
        }
        return check;
    }

    public static void supprimeCitoyens(String nom, String role) {
        citoyens.deleteOne(new Document("nom", nom).append("role", role));
        System.out.println("Citoyen supprimée : " + nom + " " + role);
    }

    /** Setter
     *@param citoyens collection
     */
    public static void setCitoyens(MongoCollection<Document> citoyens) {
        GestionCitoyens.citoyens = citoyens;
    }


}
