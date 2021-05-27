package fr.gsb.rv.vues;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.gsb.R;
import fr.gsb.rv.technique.DateFr;
import fr.gsb.rv.technique.Session;
import fr.gsb.rv.utils.RapportVisiteInsertion;

public class SaisirRapportActivity extends AppCompatActivity {
    private Button btnDate;
    private Spinner spinnerPraticiens;
    private Spinner spinnerMotifs;
    private Spinner spinnerCoeffConfiance;
    private EditText bilanRapport;
    private String dateChoisi;
    private RelativeLayout ok;
    private RelativeLayout nOk;
    private ArrayList<String> lesPraticiens = new ArrayList<>();
    private ArrayList<String> lesMotifs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir);

        btnDate = (Button) findViewById(R.id.btnDate);
        spinnerMotifs = (Spinner) findViewById(R.id.spinner_motifs);
        spinnerPraticiens = (Spinner) findViewById(R.id.spinner_praticiens);
        spinnerCoeffConfiance = (Spinner) findViewById(R.id.spinner_coeffconfiance);
        bilanRapport = (EditText) findViewById(R.id.etBilan);
        nOk = (RelativeLayout) findViewById(R.id.erreurSaisiRapport);
        ok = (RelativeLayout) findViewById(R.id.saisiOk);

        // initialisation des champs relatif a notre layout

        lesPraticiens.add("Praticiens");
        String url = String.valueOf(getResources().getString(R.string.getPraticiens));
        // appelation de notre route

        //creation de notre ecouteur
        Response.Listener<JSONArray> ecouteur = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0){
                    for(int i=0; i < response.length(); i++){
                        try {
                            JSONObject dict = response.getJSONObject(i);
                            String nomPrenom = dict.getString("pra_nom")+" "+dict.getString("pra_prenom")+" ("+dict.getString("pra_num")+")";
                            lesPraticiens.add(nomPrenom);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }else{
                    Toast.makeText(SaisirRapportActivity.this, "Erreur, aucun praticiens", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // creation d'un ecouteur d'erreur
        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SaisirRapportActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // creation de notre requete : url, methode, ecouteur

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                ecouteur,
                ecouteurErreur
        );
        RequestQueue requestQueue = Volley.newRequestQueue(SaisirRapportActivity.this);
        requestQueue.add(jsonArrayRequest);

        // creation de notre adaptateur de type String

        ArrayAdapter<String> praticiensAdapter = new ArrayAdapter<>(SaisirRapportActivity.this, R.layout.spinner_item, lesPraticiens);
        spinnerPraticiens.setAdapter(praticiensAdapter);

        // on associe notre adaptateur a notre spinner pour qu'il puisse boucler dessus

        // meme principe pour les motifs

        lesMotifs.add("Motifs");
        String url2 = String.valueOf(getResources().getString(R.string.getMotifs));
        Response.Listener<JSONArray> ecouteurMotifs = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0){
                    for(int i=0; i < response.length(); i++){
                        try {
                            JSONObject dict = response.getJSONObject(i);
                            String libelle = dict.getString("libelle_motif");
                            lesMotifs.add(libelle);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }else{
                    Toast.makeText(SaisirRapportActivity.this, "Erreur, aucun motif", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener ecouteurErreurMotifs = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SaisirRapportActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsonArrayRequestMotifs = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                ecouteurMotifs,
                ecouteurErreurMotifs
        );
        RequestQueue requestQueueMotifs = Volley.newRequestQueue(SaisirRapportActivity.this);
        requestQueueMotifs.add(jsonArrayRequestMotifs);

        ArrayAdapter<String> motifsAdaptateur = new ArrayAdapter<>(SaisirRapportActivity.this, R.layout.spinner_item, lesMotifs);
        spinnerMotifs.setAdapter(motifsAdaptateur);

        // creation d'un tableau pour les coefficients de confiance
        ArrayList<String> arrayCoeffConfiance = new ArrayList<>();

        arrayCoeffConfiance.add("Coefficient confiance");

        for(int i =5; i > 0 ; i--){
            arrayCoeffConfiance.add(String.valueOf(i));
        }


        ArrayAdapter<String> coeffConfianceAdaptateur =new ArrayAdapter<>(SaisirRapportActivity.this, R.layout.spinner_item, arrayCoeffConfiance);
        spinnerCoeffConfiance.setAdapter(coeffConfianceAdaptateur);

    }

    public void afficherDate(View v){

        DateFr dateCourante = new DateFr();
        int selectedYear = dateCourante.getAnnee();
        int selectedMonth = dateCourante.getMois() - 1;
        int selectedDayOfMonth = dateCourante.getJour();

        // ces 3 variables nous permettent de récupérer la date courante et de l'afficher dans le date
        // picker par défaut

        // Date Select Listener.

        // création d'un ecouteur
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                btnDate.setText("Date sélectionnée: "+dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                dateChoisi = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
            }
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        // initialisationn du date picker prenant en params le mois, l'annee et le jour courant

        // Show
        datePickerDialog.show();

        // affiche le date picker

    }

    public void insererRapport(View v){

        String unPraticien = spinnerPraticiens.getSelectedItem().toString();
        String unMotif = spinnerMotifs.getSelectedItem().toString();
        String bilan = bilanRapport.getText().toString();
        int coefConfiance = Integer.parseInt(spinnerCoeffConfiance.getSelectedItem().toString());

        // recuperation des valeurs des spinners et des valeurs des input dans des variables

        String[] praticien = unPraticien.split(" ");
        int numPraticien = Integer.parseInt(praticien[2].replace("(", "").replace(")",""));
        int numMotif = lesMotifs.indexOf(unMotif);
        String matriculeVisiteur = Session.getSession().getLeVisiteur().getMatricule();
        DateFr dateJour = new DateFr();
        String dateRedaction = dateJour.getAnnee()+"-"+dateJour.getMois()+"-"+dateJour.getJour();

        RapportVisiteInsertion unRapportInserer = new RapportVisiteInsertion(
                matriculeVisiteur,
                coefConfiance,
                dateRedaction,
                dateChoisi,
                bilan,
                numMotif,
                numPraticien
        );

        // initialisation d'un rapport de visite avec les valeurs récupérés précédemment

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        // initialisation de la classe gsonBuilder pour par la suite transmettre nos données au format Json


        RequestQueue requete = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.setRapport);

        //ecouteur

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ok.setVisibility(View.VISIBLE);
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                nOk.setVisibility(View.VISIBLE);
            }
        }){

            //
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> data = new HashMap<String, String>();
                data.put("rapport", gson.toJson(unRapportInserer));
                return data;
            }
        };

        requete.add(stringRequest);
    }

}

