package com.digi.entite;

public class Citoyens {
    private String nom;
    private int quantite;
    private String role;

    public Citoyens(String nom, int quantite, String role) {
        this.nom = nom;
        this.quantite = quantite;
        this.role = role;
    }

    public void afficherDetailsCitoyens() {
        System.out.println("Citoyen: " + nom + ", Quantité: " + quantite + ", Rôle: " + role);
    }
}
