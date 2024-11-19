package com.digi.div;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class GestionMission {

    private static MongoCollection<Document> missions;

    public GestionMission() {
        this.missions = missions;
    }

    public static boolean preparerMission(String nomMission, int soldatsNecessaires, int boisNecessaire, int nourritureNecessaire) {
        boolean check = false;
        if (GestionRessources.verifierRessources("Bois", boisNecessaire) && GestionRessources.verifierRessources("Nourriture", nourritureNecessaire)) {
            if (GestionCitoyens.verifierQantiteCitoyens("Soldat", "attaquant", soldatsNecessaires)) {
                check = true;
                System.out.println("Mission '" + nomMission + "' est réalisable avec les ressources et soldats actuels.");
            } else {
                System.out.println("Soldats Attaquant insuffisants");
            }
        } else {
            System.out.println("Ressources insuffisantes");
        }
        return check;
    }

    public static void envoyerMission(String nomMission, int soldatsNecessaires, int boisNecessaire, int nourritureNecessaire) {
        GestionRessources.ajouterRessources("Bois", -boisNecessaire);
        GestionRessources.ajouterRessources("Nourriture", -nourritureNecessaire);
        GestionCitoyens.ajouterCitoyens("Soldat", -soldatsNecessaires, "attaqaunt");

        System.out.println("Envoi de la mission : " + nomMission);

//        // Enregistrer la mission dans la collection des missions
//        Document mission = new Document("nom", nomMission)
//                .append("soldatsEnvoyes", soldatsNecessaires)
//                .append("status", "En cours");
//        missions.insertOne(mission);

        System.out.println("La mission " + nomMission + " a été envoyée avec " + soldatsNecessaires + " soldats.");
    }

    public static void caclulMission(String nomMission, int soldatsEnvoyes, int boisNecessaire, int nourritureNecessaire) {

        double probabiliteSucces = 0.7;

        boolean missionReussie = Math.random() < probabiliteSucces;

        if (missionReussie) {
            int gainBois = soldatsEnvoyes * 10;
            int gainNourriture = soldatsEnvoyes * 5;

            GestionRessources.ajouterRessources("Bois", gainBois);
            GestionRessources.ajouterRessources("Nourriture", gainNourriture);

//            missions.updateOne(
//                    new Document("nom", nomMission),
//                    new Document("$set", new Document("status", "Réussie"))
//                            .append("$set", new Document("resultat", "Gains"))
//                            .append("$set", new Document("gainBois", gainBois))
//                            .append("$set", new Document("gainNourriture", gainNourriture))
//            );

            System.out.println("Mission " + nomMission + " réussie ! Gains : " + gainBois + " bois, " + gainNourriture + " nourriture.");
        } else {

            int pertesSoldats = (int) (soldatsEnvoyes * 0.5);
            GestionCitoyens.ajouterCitoyens("Soldat", -pertesSoldats, "attaqaunt");

//            missions.updateOne(
//                    new Document("nom", nomMission),
//                    new Document("$set", new Document("status", "Échouée"))
//                            .append("$set", new Document("resultat", "Pertes"))
//                            .append("$set", new Document("pertesSoldats", pertesSoldats))
//            );

            System.out.println("Mission " + nomMission + " échouée ! Pertes : " + pertesSoldats + " soldats.");
        }
    }
}


