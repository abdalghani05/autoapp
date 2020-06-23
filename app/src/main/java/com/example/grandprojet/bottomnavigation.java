package com.example.grandprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class bottomnavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    database dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bottomnavigation);

        bottomNavigationView=(BottomNavigationView)findViewById (R.id.btnnav);
        if (savedInstanceState==null){
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fram,new decouvrir ()).commit ();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId ()){
                    case R.id.Découvrir:
                        fragment=new decouvrir ();
                        break;
                    case R.id.Chercher:
                        fragment=new Chercher ();
                        break;
                    case R.id.favoris:
                        if (dt.id==null){
                            fragment=new favoris ();
                        }
                        else {
                            fragment=new mesfavorispro ();
                        }
                        break;
                    case R.id.rechercher:
                        if (dt.id==null){
                            fragment=new mesrechercher ();
                        }
                        else {
                            fragment=new mesrecherechepro ();
                        }
                        break;
                    case R.id.news:
                        fragment=new news ();
                        break;
                }
                getSupportFragmentManager ().beginTransaction ().replace (R.id.fram,fragment).commit ();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        if(item.getItemId()==R.id.connection){
            if (dt.id == null) {
                i=new Intent (bottomnavigation.this,MainActivity.class);
                startActivity (i);
            }
            else {
                i=new Intent (bottomnavigation.this,profil.class);
                startActivity (i);
            }
        }
        if(item.getItemId()==R.id.info){
            i=new Intent (bottomnavigation.this,info.class);
            startActivity (i);
        }
        return super.onOptionsItemSelected(item);
    }

}
