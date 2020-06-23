package com.example.grandprojet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Chercher extends Fragment {
    int che=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate (R.layout.chercher,container,false);
       final Spinner s1,s2,s3,s4;
       final database dt;
       final SQLiteDatabase db;
        Button btn;
       final ArrayList<String> marque=new ArrayList<String>();
       final ArrayList<String> modéle=new ArrayList<String>();
       final ArrayList<String> version=new ArrayList<String>();
       final ArrayList<String> année=new ArrayList<String>();
        s1=(Spinner)view.findViewById (R.id.marque);
        s2=(Spinner)view.findViewById (R.id.modéle);
        s3=(Spinner)view.findViewById (R.id.version);
        s4=(Spinner)view.findViewById (R.id.année);
        btn=(Button)view.findViewById (R.id.btnrecherche);

        marque.add ("Tous");
        modéle.add ("Tous");
        version.add ("Tous");
        année.add ("Tous");

            dt=new database (getActivity ());
            db=dt.getReadableDatabase ();
            final Cursor cur=db.rawQuery ("select Nom from marque",null);
            while (cur.moveToNext()) {
                marque.add (cur.getString (0));
            }


           /* Cursor cur3=db.rawQuery ("select DISTINCT année from modéle order by année DESC",null);
            while (cur3.moveToNext()) {
            année.add (cur3.getString (0));
             }*/

        final ArrayAdapter<String> adt=new ArrayAdapter<String>(getActivity (),android.R.layout.simple_spinner_item,marque);
        s1.setAdapter(adt);
        ArrayAdapter<String> adt1=new ArrayAdapter<String>(getActivity (),android.R.layout.simple_spinner_item,modéle);
        s2.setAdapter(adt1);
        ArrayAdapter<String> adt2=new ArrayAdapter<String>(getActivity (),android.R.layout.simple_spinner_item,version);
        s3.setAdapter(adt2);
        ArrayAdapter<String> adt3=new ArrayAdapter<String>(getActivity (),android.R.layout.simple_spinner_item,année);
        s4.setAdapter(adt3);

        try{
            //////////////////////////////////////////////spinner 1//////////////////////////////////////////////
            s1.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition (position)!="Tous"){
                        modéle.clear ();
                        modéle.add ("Tous");
                        Cursor cur1=db.rawQuery ("select nom_modéle from modéle m,marque ma where m.id=ma.id and Nom=?",new String[] {parent.getItemAtPosition (position).toString ()});
                        while (cur1.moveToNext()) {
                            modéle.add (cur1.getString (0));
                        }
                        dt.cherchemarque=s1.getSelectedItem ().toString ();
                        dt.reche=1;
                    }
                    else {
                        modéle.clear ();
                        modéle.add ("Tous");
                        version.clear ();
                        version.add ("Tous");
                        année.clear ();
                        année.add ("Tous");
                        dt.reche=0;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            //////////////////////////////////////////////spinner 2//////////////////////////////////////////////
            s2.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition (position)!="Tous"){
                        version.clear ();
                        version.add ("Tous");
                        Cursor cur2=db.rawQuery ("select nom_version from modéle m,marque ma where m.id=ma.id and Nom=? and nom_modéle=? ",new String[] {s1.getSelectedItem ().toString (),parent.getItemAtPosition (position).toString ()});
                        while (cur2.moveToNext()) {
                            version.add(cur2.getString (0));
                        }
                        dt.cherchemodele=s2.getSelectedItem ().toString ();
                        dt.reche=2;
                    }
                    else {
                        version.clear ();
                        version.add ("Tous");
                        année.clear ();
                        année.add ("Tous");
                        dt.reche=1;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //////////////////////////////////////////////spinner 3//////////////////////////////////////////////
            s3.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition (position)!="Tous"){
                        année.clear ();
                        année.add ("Tous");
                        Cursor cur3=db.rawQuery ("select année from modéle m,marque ma where m.id=ma.id and Nom=? and nom_modéle=? and nom_version=? ",new String[] {s1.getSelectedItem ().toString (),s2.getSelectedItem ().toString (),parent.getItemAtPosition (position).toString ()});
                        while (cur3.moveToNext()) {
                            année.add (cur3.getString (0));
                        }
                        dt.chercheversion=s3.getSelectedItem ().toString ();
                        dt.reche=3;
                    }
                    else {
                        année.clear ();
                        année.add ("Tous");
                        dt.reche=2;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //////////////////////////////////////////////spinner 4//////////////////////////////////////////////
            s4.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition (position)!="Tous"){
                        dt.chercheannee=s4.getSelectedItem ().toString ();
                        dt.reche=4;
                    }
                    else {
                        dt.reche=3;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            btn.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent (getActivity (),recherchecat.class);
                    startActivity (i);
                }
            });

        }
        catch (Exception e){
            Toast.makeText (getActivity (),e.getMessage (),Toast.LENGTH_LONG).show ();
        }
        return view;
    }
}
