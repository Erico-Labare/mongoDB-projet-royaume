package com.digi.div;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class GestionCitoyens {

    private static MongoCollection<Document> citoyens;

    public GestionCitoyens(MongoCollection<Document> citoyens) {
        this.citoyens = citoyens;
    }

    public static void ajouterCitoyens(String nom, int quantite, String role) {
        Document citoyen = citoyens.find(new Document("role", role)).first();
        if (citoyen != null) {
            mettreAJourQuantiteCitoyens(nom,citoyen.getInteger("quantite", 0) + quantite, role);
        } else {
            Document nouveauCitoyen = new Document("nom", nom).append("quantite", quantite).append("role", role);
            citoyens.insertOne(nouveauCitoyen);
        }
    }

    public static void afficherCitoyens() {
        for (Document citoyens : citoyens.find()) {
            System.out.println(citoyens.toJson());
        }
    }

    public static void mettreAJourQuantiteCitoyens(String nom, int changementQuantite, String role) {
        citoyens.updateOne(new Document("role", role), new Document("$inc", new Document("quantite", changementQuantite)));
        System.out.println("Quantité mise à jour pour le rôle : " + role);
    }

    public static void mettreAJourRoleCitoyens(String nom, String ancienRole, String nouveauRole, int qteSwitch) {
        Document citoyenExistant = citoyens.find(new Document("nom", nom).append("role", ancienRole)).first();

        if (citoyenExistant != null) {
            citoyens.insertOne(new Document("nom", nom).append("quantite", 0).append("role", nouveauRole));

            mettreAJourQuantiteCitoyens(nom, -qteSwitch, ancienRole);

            mettreAJourQuantiteCitoyens(nom, qteSwitch, nouveauRole);

            System.out.println("Rôle mis à jour pour le citoyen : " + nom + " (" + ancienRole + " -> " + nouveauRole + ")");
        } else {
            Document citoyenNouveau = new Document("nom", nom)
                    .append("quantite", qteSwitch)
                    .append("role", nouveauRole);
            citoyens.insertOne(citoyenNouveau);
            System.out.println("Nouveau citoyen créé avec le rôle : " + nouveauRole);
        }
    }


    public static boolean verifierQantiteCitoyens(String nom, String role, int nombreAttendu) {
        boolean check = false;
        Document citoyen = citoyens.find(and(eq("nom", nom), eq("role", role))).first();
        if (citoyen != null) {
            int quantite = citoyen.getInteger("quantite");
            if (quantite >= nombreAttendu) {
                check = true;
            }
        }
        return check;
    }

    public static void supprimeCitoyens(String nom, String role) {
        citoyens.deleteOne(new Document("nom", nom).append("role", role));
        System.out.println("Citoyens supprimées : " + nom + " " + role);
    }

    /** Setter
     *@param citoyens collection
     */
    public static void setCitoyens(MongoCollection<Document> citoyens) {
        GestionCitoyens.citoyens = citoyens;
    }


}
