package fr.gsb.rv.vues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.entites.Motif;
import fr.gsb.rv.entites.Praticien;
import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.technique.DateFr;
import fr.gsb.rv.technique.Session;

public class ConsulterRapportActivity extends AppCompatActivity {

    private Spinner spinnerMois;
    private Spinner spinnerAnnee;
    private Button btnRechercher;
    private ArrayList<Integer> dateAnnee = new ArrayList<>();
    private ArrayList<String> dateMois = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter);
        TextView tvUserCo = (TextView) findViewById(R.id.id_userCo);
        tvUserCo.setText(Session.getSession().getLeVisiteur().getNom()+" "+Session.getSession().getLeVisiteur().getPrenom());
        spinnerMois = (Spinner) findViewById(R.id.spinner_mois);
        spinnerAnnee = (Spinner) findViewById(R.id.spinner_annee);
        btnRechercher = (Button) findViewById(R.id.boutonRechercher);

        // Methode ONCREATE
        // creation des champs correspondant a notre layout (nos 2 spinners)
        // on associe nos variables a nos spinner de notre layout
        // on cree 2 tableaux pour avoir d'un coté nos moi et d'un coté nos années

        DateFr dateFr = new DateFr();
        for (int i = 0 ; i < 10 ; i++){

            dateAnnee.add(new Integer(dateFr.getAnnee() - i));
        }

        // boucle permettant de mettre dans notre tableau les années par rapport a l'année courante

        // on ajoute nos mois a notre tableau
        dateMois.add("Janvier");
        dateMois.add("Février");
        dateMois.add("Mars");
        dateMois.add("Avril");
        dateMois.add("Mai");
        dateMois.add("Juin");
        dateMois.add("Juillet");
        dateMois.add("Août");
        dateMois.add("Septembre");
        dateMois.add("Octobre");
        dateMois.add("Novembre");
        dateMois.add("Décembre");

        // creation de 2 adaptateurs
        // on indique ou cela doit s'afficher, notre layout (design) et nos données a utiliser
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(ConsulterRapportActivity.this, R.layout.spinner_item, dateAnnee);
        ArrayAdapter<String> arrayAdapterMois = new ArrayAdapter<>(ConsulterRapportActivity.this, R.layout.spinner_item, dateMois);

        spinnerAnnee.setAdapter(arrayAdapter);
        spinnerMois.setAdapter(arrayAdapterMois);

        // on associe nos spinner a nos adapteurs

    }

    // creation d'une fonction permettant de rechercher un rapport de visite selon nos params (mois, annees)
    public void rechercherRapportVisite(View v){
        //Toast.makeText(ConsulterRapportActivity.this, "Je suis ici", Toast.LENGTH_LONG).show();
        String mois = spinnerMois.getSelectedItem().toString();
        String annee = spinnerAnnee.getSelectedItem().toString();
        // recuperation des valeurs de nos spinners

        mois = String.valueOf(this.dateMois.indexOf(mois)+1);
        mois = Integer.parseInt(mois) > 9 ? mois : "0"+mois;

        // creation d'un bundle c'est a dire un paquet qui va nous permetter d'envoyer nos données
        // d'une page a une autre
        Bundle data = new Bundle();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Integer.parseInt(mois));
        arrayList.add(Integer.parseInt(annee));

        // creation d'un tableau pour y mettre nos valeurs en les castant en int

        data.putIntegerArrayList("criteresRecherche", arrayList);

        Intent intention = new Intent(this, AfficherRapportActivity.class);
        intention.putExtras(data);
        startActivity(intention);

        // creation d'une intention qui va nous faire basculer vers une autre page
        // a l'intention on lui associe nos données et on envoi tout ca de l'autre coté
    }


}
