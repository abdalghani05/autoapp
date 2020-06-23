package com.example.grandprojet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class mesrecherechepro extends Fragment {
    AlertDialog a;
    AlertDialog.Builder b;
    String p=null;
    database dt;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final GridView gridView;
        final ArrayList<modéle> list;
        modéleadapter adapter = null;
        View view =inflater.inflate (R.layout.news, container, false);
        gridView = (GridView) view.findViewById(R.id.list1);
        list = new ArrayList<>();
        adapter = new modéleadapter (getActivity (), R.layout.listemodele, list);
        gridView.setAdapter(adapter);
        ArrayList<String> idmod=new ArrayList<> ();

        try{
            dt=new database (getActivity ());
            db=dt.getReadableDatabase ();
            Cursor cur=db.rawQuery ("select m.idmodul,ma.Nom,nom_modéle,nom_version,année,carburant,prix,m.photo from modéle m,marque ma,client c,historique h where m.id=ma.id and c.idcli=h.idcli and h.idmodul=m.idmodul and h.idcli=? order by datehis DESC",new String[]{dt.id.toString ()});
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
            gridView.setOnItemClickListener (   new AdapterView.OnItemClickListener () {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dt.idmodele = cl.getItem(position);
                    Intent i=new Intent (getActivity (),voiture.class);
                    startActivity (i);
                }
            });

            //////////////////////////////////////////////////////////////////////////////////////////

            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    p=cl.getItem(position);
                    a.show();
                    return false;
                }
            });
            b=new AlertDialog.Builder(getActivity ());
            b.setTitle("voulez vous supprimer set voiture");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dt=new database (getActivity ());
                    db=dt.getWritableDatabase ();
                    Date date = new Date();
                    db.execSQL ("delete from historique where idmodul=? and idcli=?;"
                            ,new String[] {p.toString (),dt.id.toString ()});

                }
            });
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            a=b.create();
            //////////////////////////////////////////////////////////////////////////////////////////
        }
        catch (Exception e){
            Toast.makeText (getActivity (),e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
        return view;
    }
}
