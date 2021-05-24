package fr.gsb.rv.adaptateur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.vues.DescriptionRapportActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class RapportVisiteAdaptateur extends BaseAdapter {

    private Context context;
    private ArrayList<RapportVisite> lesRapports;

    public RapportVisiteAdaptateur(Context context, ArrayList<RapportVisite> lesRapports) {
        this.context = context;
        this.lesRapports = lesRapports;
    }

    @Override
    public int getCount() {
        return lesRapports.size();
    }

    @Override
    public Object getItem(int position) {
        return lesRapports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_design, null);
        TextView praticienNom = (TextView) v.findViewById(R.id.praticien_nom);
        TextView libelleMotif = (TextView) v.findViewById(R.id.libelle_motif);
        TextView dateCreation = (TextView) v.findViewById(R.id.date_creation);
        TextView rapBilan = (TextView) v.findViewById(R.id.rap_bilan);
        TextView dateRedaction = (TextView) v.findViewById(R.id.date_redaction);
        TextView rapLu = (TextView) v.findViewById(R.id.rap_lu);

        praticienNom.setText("Praticien: "+lesRapports.get(position).getLePraticien().getNom().toUpperCase());
        libelleMotif.setText("Motif: "+lesRapports.get(position).getLeMotif().getLibelle());
        dateCreation.setText("Visité le: "+lesRapports.get(position).getDateVisite().toString());
        rapBilan.setText("Bilan: "+lesRapports.get(position).getBilan());
        dateRedaction.setText("Crée le: "+lesRapports.get(position).getDateRedaction().toString());

        if(lesRapports.get(position).isLu() == 1){
            rapLu.setText("Lu");
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, String.valueOf(lesRapports.get(position).getNumero()), Toast.LENGTH_LONG).show();
                int numRapport = lesRapports.get(position).getNumero();
                Intent intention = new Intent(context, DescriptionRapportActivity.class);

                Bundle bundle = new Bundle();

                ArrayList<Integer> arraylist = new ArrayList<>();
                arraylist.add(numRapport);
                bundle.putIntegerArrayList("numRapport", arraylist);
                intention.putExtras(bundle);
                context.startActivity(intention);
            }
        });

        return v;
    }
}
