package com.example.grandprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.InputStream;

public class creecompte extends AppCompatActivity {

    database dt;//class
    SQLiteDatabase db;//database bache dir les requte dyal sql
    TextInputLayout nom,prenom,email,motdepasse1,motdepasse2;
    CheckBox ch;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_creecompte);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        nom=(TextInputLayout)findViewById (R.id.inputnom);
        prenom=(TextInputLayout)findViewById (R.id.inputprenom);
        email=(TextInputLayout)findViewById (R.id.inputemail);
        motdepasse1=(TextInputLayout)findViewById (R.id.inputpasword);
        motdepasse2=(TextInputLayout)findViewById (R.id.inputpasword2);
        ch=(CheckBox)findViewById (R.id.check);


        dt=new database (this);
        db=dt.getReadableDatabase ();
        Cursor cursor=db.rawQuery ("select max(idcli) from client",null);
        if(cursor.getCount ()!=0){
            cursor.moveToFirst ();
            id=cursor.getInt (0);
            id=id+1;
        }
        else {
            id=1;
        }
    }
    public  void  creecompte(View v){
        if(nom==null || prenom==null || email==null || motdepasse1==null || motdepasse2==null){
            Toast.makeText (this,"remplire tous les champs",Toast.LENGTH_LONG).show ();
        }
        else{
            if(motdepasse1.getEditText ().getText ().toString ().equals (motdepasse2.getEditText ().getText ().toString ())){
                if(ch.isChecked ()){
                    try {
                        dt=new database (this);
                        db=dt.getWritableDatabase ();
                        db.execSQL ("insert into client values(?,?,?,?,?);",new String[]
                                {String.valueOf (id).toString (),nom.getEditText ().getText ().toString (),prenom.getEditText ().getText ().toString (),email.getEditText ().getText ().toString (),motdepasse1.getEditText ().getText ().toString ()});
                        dt.id=String.valueOf (id).toString ();
                        Intent i=new Intent (creecompte.this,bottomnavigation.class);
                        startActivity (i);

                    }
                    catch (Exception e){
                        Toast.makeText (this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
                    }
                }
                else
                    Toast.makeText (this,"Veuillez accepter les conditions",Toast.LENGTH_LONG).show ();
            }
            else {
                Toast.makeText (this,"Les mots de passe ne correspondent pas",Toast.LENGTH_LONG).show ();
            }
        }
    }
}
