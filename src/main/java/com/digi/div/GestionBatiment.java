package com.digi.div;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class GestionBatiment {

    private static MongoCollection<Document> batiments;

    public GestionBatiment(MongoCollection<Document> batiments, MongoCollection<Document> ressources) {
        this.batiments = batiments;
    }

    public static void ajouterBatiment(String type, int niveau, String fonction) {

        Document nouveauBatiment = new Document("type", type).append("niveau", niveau).append("fonction", fonction);
        batiments.insertOne(nouveauBatiment);

    }

    public static void construire1NiveauBatiment(String type, int coutBois, int coutPierre, String fonction) {
        Document batiment = batiments.find(new Document("fonction", fonction)).first();

//        System.out.println(ressourcesBois);
//        System.out.println(ressourcesPierre);

        if (GestionRessources.verifierRessources("Bois", coutBois) && GestionRessources.verifierRessources("Pierre",coutPierre)) {
            GestionRessources.getRessources().updateOne(
                    new Document("type", "Bois"),
                    new Document("$inc", new Document("quantite", -coutBois))
            );

            GestionRessources.getRessources().updateOne(
                    new Document("type", "Pierre"),
                    new Document("$inc", new Document("quantite", -coutPierre))
            );
            ajouterBatiment(type, 1, fonction);
            System.out.println("Bâtiment construit : " + type);
        } else {
            System.out.println("Pas assez de ressources pour construire " + type);
        }
    }

    public static void mettreAJourBatiment(String type, int nouveauNiveau, String fonction) {

        batiments.updateOne(
                new Document("type", type).append("fonction", fonction),
                new Document("$set", new Document("niveau", nouveauNiveau))
        );
    }

    public static void ameliorerBatiment(String type, int niveauAjouter, int coutBoisParNiveau, int coutPierreParNiveau, String fonction) {
        Document batiment = batiments.find(new Document("type", type).append("fonction", fonction)).first();

        if (batiment != null) {
            int niveauActuel = batiment.getInteger("niveau", 0);


            int coutBois = coutBoisParNiveau * niveauAjouter;
            int coutPierre = coutPierreParNiveau * niveauAjouter;

            if (GestionRessources.verifierRessources("Bois", coutBois) && GestionRessources.verifierRessources("Pierre",coutPierre)) {

                GestionRessources.getRessources().updateOne(
                        new Document("type", "Bois"),
                        new Document("$inc", new Document("quantite", -coutBois))
                );
                GestionRessources.getRessources().updateOne(
                        new Document("type", "Pierre"),
                        new Document("$inc", new Document("quantite", -coutPierre))
                );


                mettreAJourBatiment(type, niveauActuel + niveauAjouter, fonction);

                System.out.println("Le bâtiment " + type + " a été amélioré de " + niveauAjouter + " niveau");
            } else {
                System.out.println("Pas assez de ressources pour améliorer le bâtiment " + type);
            }
        } else {
            System.out.println("Aucun bâtiment trouvé avec le type : " + type + " et la fonction : " + fonction);
        }
    }


    public static void afficherBatiments() {
        for (Document batiments : batiments.find()) {
            System.out.println(batiments.toJson());
        }
    }

    public static void supprimeBatiment(String type, String fonction) {
        batiments.deleteOne(new Document("type", type).append("fonction", fonction));
        System.out.println("Batiment supprimée : " + type + " " + fonction);
    }

    /** Setter
     *@param batiments batiments
     */
    public static void setBatiments(MongoCollection<Document> batiments) {
        GestionBatiment.batiments = batiments;
    }
}
