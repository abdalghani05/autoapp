package com.example.grandprojet;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class marqueadt extends ArrayAdapter<String> {
    Activity act;
    ArrayList<String> idmarque;
    public marqueadt(@NonNull Activity a, ArrayList<String> b) {
        super(a,R.layout.listecatalogue,b);
        act=a;
        idmarque=b;
    }
}
