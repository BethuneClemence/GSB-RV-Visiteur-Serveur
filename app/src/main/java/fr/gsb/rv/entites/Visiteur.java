package fr.gsb.rv.entites;

import android.widget.EditText;

public class Visiteur {

    private String matricule;
    private String mdp;
    private String nom;
    private String prenom;
    private String ville;
    private String adresse;
    private String cp;

    public Visiteur(String matricule, String mdp, String nom, String prenom) {
        this.matricule = matricule;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Visiteur(String matricule, String mdp, String nom, String prenom, String ville, String adresse, String cp) {
        this.matricule = matricule;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.adresse = adresse;
        this.cp = cp;

    }

    // creation d'une classe visiteur permettant d'initialiser un visiteur
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "matricule='" + matricule + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }
}
