package com.example.grandprojet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class recherchecat extends AppCompatActivity {


    GridView gridView;
    ArrayList<modéle> list;
    modéleadapter adapter = null;
    database dt;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.recherchecat);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        gridView = (GridView) findViewById(R.id.list1);
        list = new ArrayList<>();
        adapter = new modéleadapter (recherchecat.this, R.layout.listemodele, list);
        gridView.setAdapter(adapter);
        ArrayList<String> idmod=new ArrayList<> ();


        try{
            //// ila dakholate man form recherche
            if (dt.nommarque==null){
                dt=new database (this);
                db=dt.getReadableDatabase ();
                Cursor cur;
                switch (dt.reche){
                    case 0:
                        cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id order by année DESC",null);
                        break;
                    case 1:
                        cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id and Nom=?  order by année DESC",new String[]{dt.cherchemarque.toString ()});
                        break;
                    case 2:
                        cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id and Nom=? and nom_modéle=? order by année DESC",new String[]{dt.cherchemarque.toString (),dt.cherchemodele.toString ()});
                        break;
                    case 3:
                        cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id and Nom=? and nom_modéle=? and nom_version=?  order by année DESC",new String[]{dt.cherchemarque.toString (),dt.cherchemodele.toString (),dt.chercheversion.toString ()});
                        break;
                    case 4:
                        cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id and Nom=? and nom_modéle=? and nom_version=? and année=? order by année DESC",new String[]{dt.cherchemarque.toString (),dt.cherchemodele.toString (),dt.chercheversion.toString (),dt.chercheannee});
                        break;
                    default:
                        throw new IllegalStateException ("Unexpected value: " + dt.reche);
                }
                list.clear ();
                while (cur.moveToNext()) {
                    int idmodul = cur.getInt(0);
                    idmod.add (cur.getString (0));
                    String Nom = cur.getString(1);
                    String nom_modéle = cur.getString(2);
                    String nom_version = cur.getString(3);
                    String année = cur.getString(4);
                    String carburant = cur.getString(5);
                    String prix = cur.getInt (6)+" DH";
                    byte[] image = cur.getBlob(7);

                    list.add(new modéle (idmodul, Nom, nom_modéle,nom_version,année,carburant,prix,image));
                }
            }
            else {
                //// ila dakholate man form categorie
                dt=new database (this);
                db=dt.getReadableDatabase ();
                Cursor cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id and ma.Nom=? order by année DESC",new String[]{dt.nommarque.toString ()});
                list.clear();
                while (cur.moveToNext()) {
                    int idmodul = cur.getInt(0);
                    idmod.add (cur.getString (0));
                    String Nom = cur.getString(1);
                    String nom_modéle = cur.getString(2);
                    String nom_version = cur.getString(3);
                    String année = cur.getString(4);
                    String carburant = cur.getString(5);
                    String prix = cur.getInt (6)+" DH";
                    byte[] image = cur.getBlob(7);

                    list.add(new modéle (idmodul, Nom, nom_modéle,nom_version,année,carburant,prix,image));
                }
                dt.nommarque=null;
            }

            //adabter wahd lihom bjouj
            final marqueadt cl=new marqueadt (recherchecat.this,idmod);
            adapter.notifyDataSetChanged();


            // onclick listenter dyalha
            gridView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dt.idmodele = cl.getItem(position);
                    Intent i=new Intent (recherchecat.this,voiture.class);
                    startActivity (i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText (recherchecat.this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
    }
}
