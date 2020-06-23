package com.example.grandprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class favoritevider extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.favoris);
    }

    public void connextion(View view) {
        Intent i=new Intent (favoritevider.this,MainActivity.class);
        startActivity (i);
    }
}
