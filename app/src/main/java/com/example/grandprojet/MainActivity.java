package com.example.grandprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    database dt;
    SQLiteDatabase db;
    private TextInputLayout textemail,textmotdepasse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        textemail=(TextInputLayout) findViewById (R.id.inputemail);
        textmotdepasse=(TextInputLayout)findViewById (R.id.inputpasword);
    }
    public void login(View v){
        try {
            dt=new database (this);
            db=dt.getReadableDatabase ();
            Cursor cursor=db.rawQuery ("select * from client where email=? and motdepasse=?",
                    new String[] {textemail.getEditText ().getText ().toString (),textmotdepasse.getEditText ().getText ().toString ()});
            if(cursor.getCount ()==0){
                Toast.makeText (MainActivity.this,"INVALIDE PASSWORD",Toast.LENGTH_LONG).show ();
                textmotdepasse.getEditText ().getText ().clear ();
            }
            else {
                cursor.moveToFirst ();
                dt.id=cursor.getString (0).toString ();
                Intent i=new Intent (MainActivity.this,bottomnavigation.class);
                startActivity (i);
            }
        }
        catch (Exception e){
            Toast.makeText (this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
    }
    public void signup(View v){
        Intent i=new Intent (MainActivity.this,creecompte.class);
        startActivity (i);
    }
}
