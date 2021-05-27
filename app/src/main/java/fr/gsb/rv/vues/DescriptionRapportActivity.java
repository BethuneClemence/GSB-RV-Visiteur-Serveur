package fr.gsb.rv.vues;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.adaptateur.EchantillonAdaptateur;
import fr.gsb.rv.entites.Echantillon;
import fr.gsb.rv.technique.Session;

public class DescriptionRapportActivity extends AppCompatActivity {

    private ListView listeEchantillon;
    private TextView tvUserCo;
    private ArrayList<Echantillon> lesEchantillons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_description_rapport);

        // on associe notre code java a notre layout

        listeEchantillon = (ListView) findViewById(R.id.liste_echantillon);
        // on initialise notre listeView

        tvUserCo = (TextView) findViewById(R.id.id_userCo);
        tvUserCo.setText(Session.getSession().getLeVisiteur().getNom()+" "+Session.getSession().getLeVisiteur().getPrenom());
        Bundle bundle = this.getIntent().getExtras();

        // on recupère nos donnes par le biai du bundle

        ArrayList<Integer> data = bundle.getIntegerArrayList("numRapport");
        int numRapport = data.get(0);

        // on recupère notre numero de rapport

        String url = String.format(getResources().getString(R.string.getEchantillonRapport), Session.getSession().getLeVisiteur().getMatricule(), String.valueOf(numRapport));

        // on appel la route permettant d'obtenir les echantillons pour un rapport

        Response.Listener<JSONArray> ecouteur = new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject dict = response.getJSONObject(i);
                            Echantillon echantillon = new Echantillon(
                                    dict.getString("med_nomcommercial"),
                                    dict.getInt("off_quantite"),
                                    dict.getString("med_effets")
                            );

                            // on initialise un echantillon

                            lesEchantillons.add(echantillon);

                            // on ajoute l'echantillon a notre tableau crée au préalable

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // on crée un adaptateur dans lequel on met le context, et le tableau
                    // avec lequel on va travailler
                    EchantillonAdaptateur echantillonAdaptateur = new EchantillonAdaptateur(DescriptionRapportActivity.this, lesEchantillons);
                    listeEchantillon.setAdapter(echantillonAdaptateur);

                }
            }
        };

        // ecouteur d'erreur
        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        // creation d'une requete avec la methode, url et les ecouteurs
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                ecouteur,
                ecouteurErreur
        );
        RequestQueue requestQueue = Volley.newRequestQueue(DescriptionRapportActivity.this);
        requestQueue.add(jsonArrayRequest);

        // ajout de la requete a la liste des requetes


    }
}
