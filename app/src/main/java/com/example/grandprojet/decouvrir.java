package com.example.grandprojet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class decouvrir extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        GridView gridView;
        ArrayList<marque> list;
        marqueadabter adapter = null;
        final database dt;
        SQLiteDatabase db;
        View view =inflater.inflate (R.layout.decouvrir, container, false);
        gridView = (GridView) view.findViewById(R.id.list1);
        list = new ArrayList<>();
        adapter = new marqueadabter (getActivity (), R.layout.listecatalogue, list);
        gridView.setAdapter(adapter);

        try{
            dt=new database (getActivity ());
            db=dt.getReadableDatabase ();
            Cursor cur=db.rawQuery ("select * from marque",null);
            ArrayList<String> idmar=new ArrayList<> ();
            list.clear();
            while (cur.moveToNext()) {
                int id = cur.getInt(0);
                idmar.add (cur.getString (1));
                String name = cur.getString(1);
                byte[] image = cur.getBlob(2);

                list.add(new marque (id, name, image));
            }
            final marqueadt cl=new marqueadt (getActivity (),idmar);
            adapter.notifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   dt.nommarque = cl.getItem(position);
                    Intent i=new Intent (getActivity (),recherchecat.class);
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
