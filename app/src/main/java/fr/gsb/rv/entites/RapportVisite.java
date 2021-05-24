package fr.gsb.rv.entites;


import android.os.Parcel;
import android.os.Parcelable;

import fr.gsb.rv.technique.DateFr;

public class RapportVisite {

    private int numero ;
    private String bilan ;
    private int coefConfiance ;
    private DateFr dateVisite ;
    private DateFr dateRedaction ;
    private int lu ;

    private Praticien lePraticien ;
    private Visiteur leVisiteur ;
    private Motif leMotif ;



    public RapportVisite(int numero, String bilan, int coefConfiance,
                         DateFr dateVisite, DateFr dateRedaction,
                         int lu) {

        this.numero = numero;
        this.bilan = bilan;
        this.coefConfiance = coefConfiance;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.lu = lu;
    }



    public RapportVisite(int numero, String bilan, int coefConfiance, int lu) {

        this.numero = numero;
        this.bilan = bilan;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public RapportVisite(int numero, String bilan, int coefConfiance,
                         DateFr dateVisite, DateFr dateRedaction,
                         int lu, Praticien lePraticien, Visiteur leVisiteur,
                         Motif leMotif) {

        this.numero = numero;
        this.bilan = bilan;
        this.coefConfiance = coefConfiance;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.lu = lu;
        this.lePraticien = lePraticien;
        this.leVisiteur = leVisiteur;
        this.leMotif = leMotif;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public DateFr getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(DateFr dateVisite) {
        this.dateVisite = dateVisite;
    }

    public DateFr getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(DateFr dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public int isLu() {
        return lu ;
    }

    public void setLu(int lu) {
        this.lu = lu;
    }

    public Praticien getLePraticien() {
        return lePraticien;
    }

    public void setLePraticien(Praticien lePraticien) {
        this.lePraticien = lePraticien;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public Motif getLeMotif() {
        return leMotif;
    }

    public void setLeMotif(Motif leMotif) {
        this.leMotif = leMotif;
    }

    @Override
    public String toString() {
        return "RapportVisite{" +
                "numero=" + numero +
                ", bilan='" + bilan + '\'' +
                ", coefConfiance='" + coefConfiance + '\'' +
                ", dateVisite=" + dateVisite +
                ", dateRedaction=" + dateRedaction +
                ", lu=" + lu +
                ", lePraticien=" + lePraticien +
                ", leVisiteur=" + leVisiteur +
                ", leMotif=" + leMotif +
                '}';
    }

}