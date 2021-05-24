package fr.gsb.rv.vues;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.adaptateur.RapportVisiteAdaptateur;
import fr.gsb.rv.entites.Motif;
import fr.gsb.rv.entites.Praticien;
import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.technique.DateFr;
import fr.gsb.rv.technique.Session;

public class AfficherRapportActivity extends AppCompatActivity {

    private ArrayList<RapportVisite> lesRapports = new ArrayList<>();
    private RapportVisiteAdaptateur rapportVisiteAdaptateur;
    private ListView listView;
    private TextView tvUserCo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_rapport);
        tvUserCo = (TextView) findViewById(R.id.id_userCo);
        tvUserCo.setText(Session.getSession().getLeVisiteur().getNom()+" "+Session.getSession().getLeVisiteur().getPrenom());
        listView = (ListView) findViewById(R.id.liste_rapport);
        Bundle paquet = this.getIntent().getExtras();
        ArrayList<Integer> data = paquet.getIntegerArrayList("criteresRecherche");
        String mois = String.valueOf(data.get(0));
        String annee =  String.valueOf(data.get(1));
        String url = String.format(getResources().getString(R.string.getRapportsVisite), Session.getSession().getLeVisiteur().getMatricule(), mois, annee);
        Response.Listener<JSONArray> ecouteur = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i=0; i < response.length(); i++){
                        try {
                            JSONObject dict = response.getJSONObject(i);
                            RapportVisite rapportVisite = new RapportVisite(
                                    dict.getInt("rap_num"),
                                    dict.getString("rap_bilan"),
                                    dict.getInt("rap_coef_confiance"),
                                    DateFr.parseString(dict.getString("rap_date_visite"),"en"),
                                    DateFr.parseString(dict.getString("date_redaction"), "en"),
                                    dict.getInt("rap_lu")
                            );

                            rapportVisite.setLeVisiteur(Session.getSession().getLeVisiteur());
                            Motif motif = new Motif(dict.getInt("numero_motif"), dict.getString("libelle_motif"));
                            Praticien praticien = new Praticien(dict.getInt("pra_num"), dict.getString("pra_nom"), dict.getString("pra_prenom"));
                            rapportVisite.setLeMotif(motif);
                            rapportVisite.setLePraticien(praticien);
                            lesRapports.add(rapportVisite);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    rapportVisiteAdaptateur = new RapportVisiteAdaptateur(AfficherRapportActivity.this, lesRapports);
                    listView.setAdapter(rapportVisiteAdaptateur);

                }else{
                    Toast.makeText(AfficherRapportActivity.this, "je suis la", Toast.LENGTH_LONG).show();

                }
            }
        };

        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AfficherRapportActivity.this, "Une erreur s'est produite, le serveur est injoignable !", Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                ecouteur,
                ecouteurErreur
        );
        RequestQueue requestQueue = Volley.newRequestQueue(AfficherRapportActivity.this);
        requestQueue.add(jsonArrayRequest);

        ///////////////////

    }

}
