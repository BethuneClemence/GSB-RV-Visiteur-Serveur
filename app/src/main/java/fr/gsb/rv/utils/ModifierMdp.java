package fr.gsb.rv.utils;

import android.widget.EditText;

public class ModifierMdp {

    private String matricule;
    private String confirmerMdp;

    public ModifierMdp(String matricule, String confirmerMdp) {
        this.matricule = matricule;
        this.confirmerMdp = confirmerMdp;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getConfirmerMdp() {
        return confirmerMdp;
    }

    public void setConfirmerMdp(String confirmerMdp) {
        this.confirmerMdp = confirmerMdp;
    }

    @Override
    public String toString() {
        return "ModifierMdp{" +
                "matricule='" + matricule + '\'' +
                ", confirmerMdp='" + confirmerMdp + '\'' +
                '}';
    }
}
