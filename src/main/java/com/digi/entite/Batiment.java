package com.digi.entite;

public class Batiment {
    private String type;
    private int niveau;
    private String fonction;

    public Batiment(String type, int niveau, String fonction) {
        this.type = type;
        this.niveau = niveau;
        this.fonction = fonction;
    }

    public void afficherDetailsBatiment() {
        System.out.println("Type: " + type + ", Niveau: " + niveau + ", Fonction: " + fonction);
    }
}