package com.example.arvii.dtq_30;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    EditText nom_patient, etudes_patient, cause_patient;
    Spinner annee, mois, jour;
    protected String nom_prenom, etudes, cause,anneep, moisp, jourp;
    Integer i,j,k;


    //Initialisation des éléments du menu
    private static String TAG = AccueilActivity.class.getSimpleName();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        //mise en place des differentes variables pour utiliser les champs de texte, et liste deroulante

        nom_patient = (EditText) findViewById(R.id.accueil_tf1);
        etudes_patient = (EditText) findViewById(R.id.accueil_tf2);
        cause_patient = (EditText) findViewById(R.id.accueil_tf3);

        //Remplissage des spinners

        List<Integer> spinnerArray1 =  new ArrayList<Integer>();

        for (i=1915; i<=2016; i++){
            spinnerArray1.add(i);
        }

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, spinnerArray1);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        annee = (Spinner) findViewById(R.id.spinner1);
        annee.setAdapter(adapter1);



        List<Integer> spinnerArray2 =  new ArrayList<Integer>();

        for (j=1; j<=12; j++){
            spinnerArray2.add(j);
        }

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, spinnerArray2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mois = (Spinner) findViewById(R.id.spinner2);
        mois.setAdapter(adapter2);


        List<Integer> spinnerArray3 =  new ArrayList<Integer>();

        for (k=1; k<=31; k++){
            spinnerArray3.add(k);
        }

        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, spinnerArray3);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        jour = (Spinner) findViewById(R.id.spinner3);
        jour.setAdapter(adapter3);


        Button demarrer = (Button) findViewById(R.id.accueil_pb2);
        demarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nom_prenom = nom_patient.getText().toString();
                etudes = etudes_patient.getText().toString();
                cause = cause_patient.getText().toString();
                anneep = annee.getSelectedItem().toString();
                moisp = mois.getSelectedItem().toString();
                jourp = jour.getSelectedItem().toString();

                if (nom_prenom.matches("") || etudes.matches("")) {
                    Toast.makeText(AccueilActivity.this, "Erreur de remplissage d'un des champs ",
                            Toast.LENGTH_LONG).show();
                } else  {

                    Intent intent = new Intent(AccueilActivity.this, ParameterActivity.class);
                    startActivity(intent);
                }
            }

        });


        mNavItems.add(new NavItem("Tutoriel", "Comment utiliser le DTQ-30", R.drawable.tuto));
        mNavItems.add(new NavItem("Paramètres", "Modifiez vos reglages", R.drawable.param));
        mNavItems.add(new NavItem("À propos", "Plus sur le DTQ-30", R.drawable.apropos));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

    }

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }


    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    private void selectItemFromDrawer(int position) {
        switch (position){
            case 0 :
                //Intent Intent0 = new Intent(AccueilActivity.this, TutorialActivity.class);
                //startActivity(Intent0);
                break;
            case 1 :
                Intent Intent1 = new Intent(AccueilActivity.this, ParameterActivity.class);
                startActivity(Intent1);
                break;
            case 2 :
                Intent Intent2 = new Intent(AccueilActivity.this, ProposActivity.class);
                startActivity(Intent2);
                break;
        }
    }

}
