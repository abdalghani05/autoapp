package com.example.grandprojet;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class modeleadt extends ArrayAdapter<String> {
    Activity act;
    ArrayList<String> idmodele;
    public modeleadt(@NonNull Activity a, ArrayList<String> b) {
        super(a,R.layout.listemodele,b);
        act=a;
        idmodele=b;
    }
}
