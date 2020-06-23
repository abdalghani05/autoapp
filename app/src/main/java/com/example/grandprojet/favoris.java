package com.example.grandprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class favoris extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate (R.layout.favoris, container, false);
        final Button button=(Button)view.findViewById (R.id.btnlogin);
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (getActivity (),MainActivity.class);
                startActivity (i);
            }
        });
        return view;
    }

}
