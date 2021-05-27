package fr.gsb.rv.vues;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;
import fr.gsb.rv.vues.ConnectionActivity;

public class MenuActivity extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            TextView tvUserCo = (TextView) findViewById(R.id.id_userCo);
            tvUserCo.setText(Session.getSession().getLeVisiteur().getNom()+" "+Session.getSession().getLeVisiteur().getPrenom());
        }
        // methode ONCREATE
        // on associe notre layout a notre code JAVA
        // on récupère l'utilisateur connecté pour l'afficher sur notre vue

        // creation de la methode CONSULTER RAPPORT
        // cette methode est associe au bouton consulter un rapport et permet de nous faire basculer vers la vue consulter
        public void consulterRapport(View v){
            final Intent intent = new Intent(MenuActivity.this, ConsulterRapportActivity.class);
            startActivity(intent);

        }

        // creation de la methode SAISIR RAPPORT
        // cette methode est associe au bouton saisir rapport et permet de nous faire basculer faire la vue saisir
        public void saisirRapport(View v){
            final Intent intent = new Intent(MenuActivity.this, SaisirRapportActivity.class);
            startActivity(intent);

        }

        // creation de la methode consulterProfil
        // cette methode est associe au bouton profil et permet de nous faire basculer vers la vue consulte profil
        public void consulterProfil(View v){
            final Intent intent = new Intent(MenuActivity.this, ProfilActivity.class);
            startActivity(intent);

        }


};


