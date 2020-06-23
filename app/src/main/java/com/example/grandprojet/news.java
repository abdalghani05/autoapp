package com.example.grandprojet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class news extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final GridView gridView;
        final ArrayList<modéle> list;
        modéleadapter adapter = null;
        final database dt;
        SQLiteDatabase db;
        View view =inflater.inflate (R.layout.news, container, false);
        gridView = (GridView) view.findViewById(R.id.list1);
        list = new ArrayList<>();
        adapter = new modéleadapter (getActivity (), R.layout.listemodele, list);
        gridView.setAdapter(adapter);
        ArrayList<String> idmod=new ArrayList<> ();

        try{
            dt=new database (getActivity ());
            db=dt.getReadableDatabase ();
            Cursor cur=db.rawQuery ("select idmodul,Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma where m.id=ma.id order by année DESC",null);
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
            final marqueadt cl=new marqueadt (getActivity (),idmod);
            adapter.notifyDataSetChanged();
            gridView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dt.idmodele = cl.getItem(position);
                    Intent i=new Intent (getActivity (),voiture.class);
                    startActivity (i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText (getActivity (),e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
        return view;
    }
}
