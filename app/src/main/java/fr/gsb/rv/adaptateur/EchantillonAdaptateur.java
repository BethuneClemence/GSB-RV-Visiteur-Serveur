package fr.gsb.rv.adaptateur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.entites.Echantillon;

public class EchantillonAdaptateur extends BaseAdapter {

    private Context context;
    private ArrayList<Echantillon> lesEchantillons;

    public EchantillonAdaptateur(Context context, ArrayList<Echantillon> lesEchantillons) {
        this.context = context;
        this.lesEchantillons = lesEchantillons;
    }

    @Override
    public int getCount() {
        return lesEchantillons.size();
    }

    @Override
    public Object getItem(int position) {
        return lesEchantillons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = View.inflate(context, R.layout.layout_echantillon_design, null);
        // on associe notre design layout a notre code java

        TextView medocNom = (TextView) v.findViewById(R.id.medoc_nom);
        TextView medocEffets = (TextView) v.findViewById(R.id.medoc_effets);
        TextView medocQuantite = (TextView) v.findViewById(R.id.medoc_quantite);
        // on initialise nos champs

        medocNom.setText("Nom echantillon: "+lesEchantillons.get(position).getMedNonCommercial());
        medocEffets.setText("Effets: "+lesEchantillons.get(position).getMedEffets());
        medocQuantite.setText("Quantité: "+String.valueOf(lesEchantillons.get(position).getQuantite()));

        // on met dans nos champs, les valeurs correspondantes



        return v;
    }
}
