package fr.gsb.rv.vues;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;
import fr.gsb.rv.utils.ModifierMdp;

public class ModifierMdpActivity extends AppCompatActivity {

    private EditText nouveauMdp;
    private EditText confirmerMdp;
    private EditText ancienMdp;
    private RelativeLayout nOkMdp;
    private RelativeLayout okMdp;
    private TextView erreurMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mdp);

        // on associe notre code java a notre layout

        ancienMdp = (EditText) findViewById(R.id.ancienMdp);
        nouveauMdp = (EditText) findViewById(R.id.nvxMdp);
        confirmerMdp = (EditText) findViewById(R.id.confirmerMdp);
        nOkMdp = (RelativeLayout) findViewById(R.id.erreurMdp);
        okMdp =  (RelativeLayout) findViewById(R.id.mdpModifie);
        erreurMsg = (TextView) findViewById(R.id.erreurMessage);

        // initialisation de nos champs lié a notre layout


    }

    public void updateMdp(View v){
        //creation d'une fonction associé au bouton
        String ancienMotDePasse = ancienMdp.getText().toString();
        String confirmerMotDePasse = confirmerMdp.getText().toString();
        String nouveauMotDePasse = nouveauMdp.getText().toString();

        // recuperation de nos champs et des valeurs

        if(ancienMotDePasse.toLowerCase().equals(Session.getSession().getLeVisiteur().getMdp())){
            //comparaison entre l'ancien mot de passe et celui que l'utilisateur va ecrire
           if(nouveauMotDePasse.equals(confirmerMotDePasse)){
                // comparaison entre le nouveau et la confirmation du nouveau mdp
               String matricule = Session.getSession().getLeVisiteur().getMatricule();
                // recuperation du matricule du visiteur
               ModifierMdp modificationMdp = new ModifierMdp(matricule, confirmerMotDePasse);
                // initialisation de l'adaptateur recevant le nouveau mot de passe et le matricule

               GsonBuilder gsonBuilder = new GsonBuilder();
               Gson gson = gsonBuilder.create();
               // /!\ a revoir
               RequestQueue requete = Volley.newRequestQueue(this);
               String url = getResources().getString(R.string.setMdp);

               // appelation de la route

               //creation d'un ecouteur de type POST
               StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               okMdp.setVisibility(View.VISIBLE);
                               // on rend l'alert visible lorsque le mdp est bien modifé

                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               nOkMdp.setVisibility(View.VISIBLE);
                               // on rend l'alerte visible lorsque le mdp n'a pas reussi a etre modifié

                           }
                       }){
                   @Override
                   protected Map<String, String> getParams()
                   {
                       Map<String, String> data = new HashMap<String, String>();
                       data.put("data", gson.toJson(modificationMdp));
                       return data;
                   }
               };

               requete.add(stringRequest);


           }else{
               erreurMsg.setText("Mots de passe différents !");
               nOkMdp.setVisibility(View.VISIBLE);
           }



        }else{
            erreurMsg.setText("L'ancien mot de passe est incorrect !");
            nOkMdp.setVisibility(View.VISIBLE);
        }
    }
}
