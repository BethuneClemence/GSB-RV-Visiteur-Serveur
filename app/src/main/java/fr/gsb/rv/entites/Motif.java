package fr.gsb.rv.entites;

public class Motif {

    private int code ;
    private String libelle ;

    public Motif(int code, String libelle) {
        super();
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }


}
