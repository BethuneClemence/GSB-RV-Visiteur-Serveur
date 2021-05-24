package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import fr.gsb.rv.vues.ConnectionActivity;

public class MainActivity extends AppCompatActivity {

    private String matricule;
    private String mdp;
    private EditText matriculeInput;
    private EditText mdpInput;
    private Button buttonAnnuler;
    private Button buttonValider;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, ConnectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
            }
        }, 3000);

        /*this.matriculeInput = (EditText) findViewById(R.id.etMatricule);
        this.mdpInput = (EditText) findViewById(R.id.etMdp);
        this.buttonAnnuler = (Button) findViewById(R.id.bAnnuler);
        this.buttonValider = (Button) findViewById(R.id.bValider);
        this.alert = (TextView) findViewById(R.id.tvErreur);*/

    }

    /*public void seConnecter(View v) throws UnsupportedEncodingException {
        //Toast.makeText(MainActivity.this ,"Bouton validé cliqué", Toast.LENGTH_LONG).show();
        matricule = matriculeInput.getText().toString();
        mdp = mdpInput.getText().toString();

        String identifiants = matricule+"."+mdp;
        final String urlEncode = URLEncoder.encode(identifiants, "UTF-8");
        String url = String.format(getResources().getString(R.string.seConnecter_IP), urlEncode);

        //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
        Response.Listener<JSONObject> ecouteur = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Visiteur visiteur = new Visiteur(
                            response.getString("vis_matricule"),
                            response.getString("vis_prenom"),
                            response.getString("vis_nom"),
                            mdp
                    );

                    Toast.makeText(MainActivity.this, visiteur.toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                alert.setText("Une erreur s'est produite, veuillez réessayer !");
            }
        };


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, ecouteur,ecouteurErreur
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

        /*Visiteur visiteur = ModeleGsb.getInstance().seConnecter(matricule, mdp);
        if(visiteur == null){
            alert.setText("Oups, il y a une erreur ... ");
        }else{
            alert.setText("");
            Toast.makeText(MainActivity.this , "Vou êtes connecté !  ", Toast.LENGTH_LONG).show();
        }


    }*/

    public void annuler(View v){
        //Toast.makeText(MainActivity.this, "Bouton annuler cliqué", Toast.LENGTH_LONG).show();
        matriculeInput.setText("");
        mdpInput.setText("");
    }



}