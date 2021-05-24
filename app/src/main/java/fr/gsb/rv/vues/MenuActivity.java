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

        public void consulterRapport(View v){
            final Intent intent = new Intent(MenuActivity.this, ConsulterRapportActivity.class);
            startActivity(intent);

        }

    public void saisirRapport(View v){
        final Intent intent = new Intent(MenuActivity.this, SaisirRapportActivity.class);
        startActivity(intent);

    }


};


