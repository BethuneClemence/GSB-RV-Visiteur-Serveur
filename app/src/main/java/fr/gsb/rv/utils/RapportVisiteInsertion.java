package fr.gsb.rv.utils;

public class RapportVisiteInsertion {

    private String matricule;
    private int coefConfiance;
    private String dateRedaction;
    private String dateVisite;
    private String bilan;
    private int numMotif;
    private int numPraticien;

    public RapportVisiteInsertion(String matricule, int coefConfiance, String dateRedaction, String dateVisite, String bilan, int numMotif, int numPraticien) {
        this.matricule = matricule;
        this.coefConfiance = coefConfiance;
        this.dateRedaction = dateRedaction;
        this.dateVisite = dateVisite;
        this.bilan = bilan;
        this.numMotif = numMotif;
        this.numPraticien = numPraticien;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public String getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(String dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public int getNumMotif() {
        return numMotif;
    }

    public void setNumMotif(int numMotif) {
        this.numMotif = numMotif;
    }

    public int getNumPraticien() {
        return numPraticien;
    }

    public void setNumPraticien(int numPraticien) {
        this.numPraticien = numPraticien;
    }

    @Override
    public String toString() {
        return "RapportVisiteInsertion{" +
                "matricule='" + matricule + '\'' +
                ", coefConfiance=" + coefConfiance +
                ", dateRedaction='" + dateRedaction + '\'' +
                ", dateVisite='" + dateVisite + '\'' +
                ", bilan='" + bilan + '\'' +
                ", numMotif=" + numMotif +
                ", numPraticien=" + numPraticien +
                '}';
    }
}
