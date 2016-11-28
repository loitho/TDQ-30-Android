package com.example.arvii.dtq_30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by arvii on 24/11/16.
 */
public class ResultatsActivity extends AppCompatActivity {

    TextView tv_pointage, tv_erreurs_semantique, tv_erreurs_phonetique, tv_aucune_reponse, tv_indice_semantique, tv_indice_phonetique;
    int pointage, erreurs_semantique, erreurs_phonetique, aucune_reponse, indice_semantique, indice_phonetique;
    Button nouveau_test, enregistrer, envoyer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);

        Intent mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();

        if (bundle != null) {
            pointage = bundle.getInt("bonne reponse");
            erreurs_semantique = bundle.getInt("erreur semantique");
            erreurs_phonetique = bundle.getInt("erreur phonetique");
            aucune_reponse = bundle.getInt("aucune reponse");
            indice_semantique = bundle.getInt("indice semantique");
            indice_phonetique = bundle.getInt("indice phonetique");
        }

        tv_pointage = (TextView) findViewById(R.id.resultats_tv2);
        tv_erreurs_semantique = (TextView) findViewById(R.id.resultats_tv3);
        tv_indice_semantique = (TextView) findViewById(R.id.resultats_tv4);
        tv_aucune_reponse = (TextView) findViewById(R.id.resultats_tv5);
        tv_erreurs_phonetique = (TextView) findViewById(R.id.resultats_tv6);
        tv_indice_phonetique = (TextView) findViewById(R.id.resultats_tv7);


        tv_pointage.setText("Bonnes réponses : " + String.valueOf(pointage) + " / 30");
        tv_erreurs_semantique.setText("Nombre d'erreurs sémantiques : " + String.valueOf(erreurs_semantique) + " / 30");
        tv_erreurs_phonetique.setText("Nombre d'erreurs phonétiques : " + String.valueOf(erreurs_phonetique) + " / 30");
        tv_aucune_reponse.setText("Mots non trouvés : " + String.valueOf(aucune_reponse) + " / 30");
        tv_indice_semantique.setText("Nombre d'indices sémantiques : " + String.valueOf(indice_semantique) + " / 30");
        tv_indice_phonetique.setText("Nombre d'indices phonetiques : " + String.valueOf(indice_phonetique) + " / 30");


        nouveau_test = (Button) findViewById(R.id.button4);
        nouveau_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatsActivity.this, AccueilActivity.class);
                startActivity(intent);
            }
        });
    }

}
