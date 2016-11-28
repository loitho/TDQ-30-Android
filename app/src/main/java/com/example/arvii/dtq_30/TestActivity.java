package com.example.arvii.dtq_30;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by arvii on 17/11/16.
 */
public class TestActivity extends AppCompatActivity{

    ImageView image;
    Button btn1, btn2, btn3, btn4, indice_sem, indice_phone;
    int bonne_reponse, aucune_reponse, erreur_semantique, erreur_phonetique, indice_semantique, indice_phonetique, i;
    int test_number, va2;



    protected int[] Images = new int[]{R.drawable.imageexemple,R.drawable.imagedebut_test,
                                        R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
                                        R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,
                                        R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,
                                        R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,
                                        R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20,
                                        R.drawable.image21,R.drawable.image22,R.drawable.image23,R.drawable.image24,
                                        R.drawable.image25,R.drawable.image26,R.drawable.image27,R.drawable.image28,
                                        R.drawable.image29,R.drawable.image30,
                                        R.drawable.imagefin_test};

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

        Intent mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();

        if (bundle != null) {
            test_number = bundle.getInt("Test Screen");
            bonne_reponse = bundle.getInt("bonne reponse");
            aucune_reponse = bundle.getInt("aucune reponse");
            erreur_semantique = bundle.getInt("erreur semantique");
            erreur_phonetique = bundle.getInt("erreur phonetique");
            indice_semantique = bundle.getInt("indice semantique");
            indice_phonetique = bundle.getInt("indice phonetique");
            i = bundle.getInt("i");

        }

        // mise en place du fond
        onTeste(test_number);

        image.setImageResource(Images[i]);
        image.setTag(Images[i]);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                bonne_reponse++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                aucune_reponse++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                erreur_semantique++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                erreur_phonetique++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }

            }
        });

        indice_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                indice_phonetique++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }
            }
        });

        indice_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                image.setImageResource(Images[i]);
                indice_semantique++;
                if ( Images[i] == R.drawable.imagefin_test ){
                    finTest(bonne_reponse, erreur_phonetique, erreur_semantique, aucune_reponse, indice_phonetique, indice_semantique);
                }

            }
        });

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

    public void onTeste (int test_number){

        if (test_number == 1 ){

            setContentView(R.layout.activity_test1);
            image = (ImageView) findViewById(R.id.test1_iv);
            btn1 = (Button) findViewById(R.id.test1_pb1);
            btn2 = (Button) findViewById(R.id.test1_pb2);
            btn3 = (Button) findViewById(R.id.test1_pb3);
            btn4 = (Button) findViewById(R.id.test1_pb4);;
            indice_sem = (Button) findViewById(R.id.test1_pb5);
            indice_phone = (Button) findViewById(R.id.test1_pb6);
        }else if ( test_number == 2 ){

            setContentView(R.layout.activity_test2);
            image = (ImageView) findViewById(R.id.test2_iv);
            btn1 = (Button) findViewById(R.id.test2_pb1);
            btn2 = (Button) findViewById(R.id.test2_pb2);
            btn3 = (Button) findViewById(R.id.test2_pb3);
            btn4 = (Button) findViewById(R.id.test2_pb4);
            indice_sem = (Button) findViewById(R.id.test2_pb5);
            indice_phone = (Button) findViewById(R.id.test2_pb6);
        }else if (test_number == 3 ){

            setContentView(R.layout.activity_test3);
            image = (ImageView) findViewById(R.id.test3_iv);
            btn1 = (Button) findViewById(R.id.test3_pb1);
            btn2 = (Button) findViewById(R.id.test3_pb2);
            btn3 = (Button) findViewById(R.id.test3_pb3);
            btn4 = (Button) findViewById(R.id.test3_pb4);
            indice_sem = (Button) findViewById(R.id.test3_pb5);
            indice_phone = (Button) findViewById(R.id.test3_pb6);
        }
    }

    public void finTest (final int bonne_reponse, final int erreur_phonetique, final int erreur_semantique, final int aucune_reponse, final int indice_phonetique, final int indice_semantique){

        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn4.setVisibility(View.INVISIBLE);
        indice_phone.setVisibility(View.INVISIBLE);
        indice_sem.setVisibility(View.INVISIBLE);
        btn1.setText("Afficher les résultats");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, ResultatsActivity.class);
                intent.putExtra("bonne reponse",bonne_reponse);
                intent.putExtra("erreur semantique",erreur_semantique);
                intent.putExtra("erreur phonetique",erreur_phonetique);
                intent.putExtra("aucune reponse",aucune_reponse);
                intent.putExtra("indice phonetique",indice_phonetique);
                intent.putExtra("indice semantique",indice_semantique);
                startActivity(intent);
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
                Intent Intent1 = new Intent(TestActivity.this, ParameterActivity.class);
                Intent1.putExtra("bonne reponse",bonne_reponse);
                Intent1.putExtra("erreur semantique",erreur_semantique);
                Intent1.putExtra("erreur phonetique",erreur_phonetique);
                Intent1.putExtra("aucune reponse",aucune_reponse);
                Intent1.putExtra("indice phonetique",indice_phonetique);
                Intent1.putExtra("indice semantique",indice_semantique);
                Intent1.putExtra("i",i);
                startActivity(Intent1);
                break;
            case 2 :
                Intent Intent2 = new Intent(TestActivity.this, ProposActivity.class);
                startActivity(Intent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }

}
