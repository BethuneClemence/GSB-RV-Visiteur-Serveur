package fr.gsb.rv.vues;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;

public class ProfilActivity extends AppCompatActivity {

    private EditText nomVisiteur;
    private EditText prenomVisiteur;
    private EditText adresseVisiteur;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nomVisiteur = (EditText) findViewById(R.id.nomVisiteur);
        prenomVisiteur = (EditText) findViewById(R.id.prenomVisiteur);
        adresseVisiteur = (EditText) findViewById(R.id.adresseVisiteur);
        // initialisation de nos champs de notre layout
        nomVisiteur.setEnabled(false);
        prenomVisiteur.setEnabled(false);
        adresseVisiteur.setEnabled(false);

        // on rend les chammps non cliquable

        prenomVisiteur.setText(Session.getSession().getLeVisiteur().getPrenom());
        nomVisiteur.setText(Session.getSession().getLeVisiteur().getNom().toUpperCase());
        adresseVisiteur.setText(Session.getSession().getLeVisiteur().getAdresse()+ " - "+Session.getSession().getLeVisiteur().getVille()+", "+Session.getSession().getLeVisiteur().getCp());
        
        // on y met les valeurs correspndantes
    }

    public void modifierMdp(View v){
        final Intent intent = new Intent(ProfilActivity.this, ModifierMdpActivity.class);
        startActivity(intent);
        
        // on crée une intention associé a un bouton permettant par modification du mot de passe

    }

}
