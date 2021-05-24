package fr.gsb.rv.entites;

public class Echantillon {
    private String medNonCommercial;
    private int quantite;
    private String medEffets;

    public Echantillon(String medNonCommercial, int quantite, String medEffets) {


        this.medNonCommercial = medNonCommercial;
        this.quantite = quantite;
        this.medEffets = medEffets;
    }

    public String getMedNonCommercial() {
        return medNonCommercial;
    }

    public void setMedNonCommercial(String medNonCommercial) {
        this.medNonCommercial = medNonCommercial;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }



    public String getMedEffets() {
        return medEffets;
    }

    public void setMedEffets(String medEffets) {
        this.medEffets = medEffets;
    }

    @Override
    public String toString() {
        return "Echantillon{" +
                "medNonCommercial='" + medNonCommercial + '\'' +
                ", quantite=" + quantite +
                ", medEffets='" + medEffets + '\'' +
                '}';
    }
}
