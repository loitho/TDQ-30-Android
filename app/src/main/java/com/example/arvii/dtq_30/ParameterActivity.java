package com.example.arvii.dtq_30;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class  ParameterActivity extends AppCompatActivity {

    protected String nomc;
    EditText nom;
    SeekBar barre_luminosite;
    int luminosite;
    ContentResolver cresolver;
    Window window;
    RadioGroup radio_group;
    RadioButton bouton_envers, bouton_droite, bouton_gauche;
    Button debut_test;
    int ts, va2, bonne_reponse, aucune_reponse, erreur_semantique, erreur_phonetique, indice_phonetique, indice_semantique,  i;
    ImageView petit, grand;


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
        setContentView(R.layout.activity_parameter);

        Intent mIntent = getIntent();

        Bundle bundle = mIntent.getExtras();

        if (bundle != null) {
            bonne_reponse = bundle.getInt("bonne reponse");
            aucune_reponse = bundle.getInt("aucune reponse");
            erreur_semantique = bundle.getInt("erreur semantique");
            erreur_phonetique = bundle.getInt("erreur phonetique");
            indice_semantique = bundle.getInt("indice semantique");
            indice_phonetique = bundle.getInt("indice phonetique");
            i = bundle.getInt("i");
        }else {
            bonne_reponse = 0;
            aucune_reponse = 0;
            erreur_semantique = 0;
            erreur_phonetique = 0;
            indice_phonetique = 0;
            indice_semantique = 0;
            i = 0;
        }

        petit = (ImageView) findViewById(R.id.imageView);
        petit.setImageResource(R.drawable.lumpetit);
        grand = (ImageView) findViewById(R.id.imageView2);
        grand.setImageResource(R.drawable.lumgrd);
        nom = (EditText) findViewById(R.id.parameter_tf);

        bouton_envers = (RadioButton) findViewById(R.id.radioButton2);
        bouton_envers.setButtonDrawable(R.drawable.selector_face);
        bouton_envers.setChecked(true);
        bouton_droite = (RadioButton) findViewById(R.id.radioButton);
        bouton_droite.setButtonDrawable(R.drawable.selector_droite);
        bouton_gauche = (RadioButton) findViewById(R.id.radioButton3);
        bouton_gauche.setButtonDrawable(R.drawable.selector_gauche);

        radio_group = (RadioGroup) findViewById(R.id.radioGroup);


        //Bouton démarrer test
        debut_test = (Button) findViewById(R.id.button2);
        debut_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParameterActivity.this, TestActivity.class);

                if (bouton_gauche.isChecked() == true){
                    ts = 2;
                }

                if (bouton_envers.isChecked() == true){
                    ts = 1;
                }

                if (bouton_droite.isChecked() == true){
                    ts = 3;
                }


                Toast.makeText(ParameterActivity.this, String.valueOf(ts),
                        Toast.LENGTH_LONG).show();
                intent.putExtra("Test Screen", ts);
                intent.putExtra("bonne reponse",bonne_reponse);
                intent.putExtra("erreur semantique",erreur_semantique);
                intent.putExtra("erreur phonetique",erreur_phonetique);
                intent.putExtra("aucune reponse",aucune_reponse);
                intent.putExtra("indice phonetique",indice_phonetique);
                intent.putExtra("indice semantique",indice_semantique);
                intent.putExtra("i",i);
                startActivity(intent);
            }
        });


        //Gestion luminosité

        barre_luminosite = (SeekBar) findViewById(R.id.seekBar);

        cresolver = getContentResolver();

        window = getWindow();

                //Set the seekbar range between 0 and 255
                //seek bar settings//
                //sets the range between 0 and 255
                barre_luminosite.setMax(255);
                //set the seek bar progress to 1
                barre_luminosite.setKeyProgressIncrement(1);

                try {
                    //Get the current system brightness
                    luminosite = System.getInt(cresolver, System.SCREEN_BRIGHTNESS);
                    //Settings.System.putInt(cresolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);


                } catch (SettingNotFoundException e) {
                    //Throw an error case it couldn't be retrieved
                    Log.e("Error", "Cannot access system brightness");
                    e.printStackTrace();
                }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (Settings.System.canWrite(this) == false){

                        if (!Settings.System.canWrite(getApplicationContext())) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, 200);
                        }
                        //Set the progress of the seek bar based on the system's brightness
                        barre_luminosite.setProgress(luminosite);

                        //Register OnSeekBarChangeListener, so it can actually change values
                        barre_luminosite.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            public void onStopTrackingTouch(SeekBar seekbar) {
                                //Set the system brightness using the brightness variable value
                                System.putInt(cresolver, System.SCREEN_BRIGHTNESS, luminosite);
                                //Get the current window attributes
                                LayoutParams layoutpars = window.getAttributes();
                                //Set the brightness of this window
                                layoutpars.screenBrightness = luminosite / (float) 255;
                                //Apply attribute changes to this window
                                window.setAttributes(layoutpars);
                            }

                            public void onStartTrackingTouch(SeekBar seekBar) {
                                //Nothing handled here
                            }

                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                //Set the minimal brightness level
                                //if seek bar is 20 or any value below
                                if (progress <= 20) {
                                    //Set the brightness to 20
                                    luminosite = 20;
                                } else //brightness is greater than 20
                                {
                                    //Set brightness variable based on the progress bar
                                    luminosite = progress;
                                }
                                //Calculate the brightness percentage
                                float perc = (luminosite / (float) 255) * 100;
                            }
                        });

                    }
                }

        mNavItems.add(new NavItem("Tutoriel", "Comment utiliser le DTQ-30", R.drawable.image2));
        mNavItems.add(new NavItem("Paramètres", "Modifiez vos reglages", R.drawable.image3));
        mNavItems.add(new NavItem("À propos", "Plus sur le DTQ-30", R.drawable.image4));

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
                //Intent Intent1 = new Intent(ParameterActivity.this, ParameterActivity.class);
                //startActivity(Intent1);
                break;
            case 2 :
                Intent Intent2 = new Intent(ParameterActivity.this, ProposActivity.class);
                startActivity(Intent2);
                break;
        }
    }


}
