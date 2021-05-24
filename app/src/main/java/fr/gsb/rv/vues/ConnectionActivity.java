package fr.gsb.rv.vues;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import fr.gsb.MainActivity;
import fr.gsb.R;
import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.technique.Session;

public class ConnectionActivity extends AppCompatActivity {

    private String matricule;
    private String mdp;
    private EditText matriculeInput;
    private EditText mdpInput;
    private Button buttonAnnuler;
    private Button buttonValider;
    private TextView alert;
    private RelativeLayout alertConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        this.matriculeInput = (EditText) findViewById(R.id.etMatricule);
        this.mdpInput = (EditText) findViewById(R.id.etMdp);
        //this.buttonAnnuler = (Button) findViewById(R.id.bAnnuler);
        this.buttonValider = (Button) findViewById(R.id.bValider);
        //this.alert = (TextView) findViewById(R.id.tvErreur);
        this.alertConnexion = (RelativeLayout) findViewById(R.id.erreurCo);


    }

    public void seConnecter(View v) throws UnsupportedEncodingException {
        //Toast.makeText(MainActivity.this ,"Bouton validé cliqué", Toast.LENGTH_LONG).show();
        matricule = matriculeInput.getText().toString();
        mdp = mdpInput.getText().toString();

        String identifiants = matricule + "." + mdp;
        final String urlEncode = URLEncoder.encode(identifiants, "UTF-8");
        String url = String.format(getResources().getString(R.string.seConnecter_IP), urlEncode);

        //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
        Response.Listener<JSONObject> ecouteur = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.length() > 0) {

                        Visiteur visiteur = new Visiteur(
                                response.getString("vis_matricule"),
                                mdp,
                                response.getString("vis_nom"),
                                response.getString("vis_prenom")
                        );

                        Session.ouvrir(visiteur);
                        //Toast.makeText(ConnectionActivity.this, visiteur.toString(), Toast.LENGTH_LONG).show();
                        final Intent intent = new Intent(ConnectionActivity.this, MenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);

                    }else {
                        alertConnexion.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConnectionActivity.this, "Une erreur s'est produite, le serveur est injoignable !", Toast.LENGTH_LONG).show();
                //alert.setText("Une erreur s'est produite, le serveur est injoignable !");
            }
        };


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    ecouteur,
                    ecouteurErreur
            );
            RequestQueue requestQueue = Volley.newRequestQueue(ConnectionActivity.this);
            requestQueue.add(jsonObjectRequest);


        
    }

    /*public void annuler(View v){
        //Toast.makeText(MainActivity.this, "Bouton annuler cliqué", Toast.LENGTH_LONG).show();
        matriculeInput.setText("");
        mdpInput.setText("");
    }*/

}