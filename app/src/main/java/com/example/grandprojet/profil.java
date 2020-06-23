package com.example.grandprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class profil extends AppCompatActivity {

    final String email=null;
    TextView txtnom,txtprenom,txtemail;
    database dt;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profil);
        Intent i=getIntent();

        txtnom=(TextView)findViewById (R.id.txtnom);
        txtprenom=(TextView)findViewById (R.id.txtprenom);
        txtemail=(TextView)findViewById (R.id.txtemail);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        try {
            dt=new database (this);
            db=dt.getReadableDatabase ();
            Cursor cur=db.rawQuery ("select * from client where idcli=?",new String[] {dt.id.toString ()});
            if(cur.getCount ()!=0  ){
                cur.moveToFirst ();
                txtnom.setText (cur.getString (1));
                txtprenom.setText (cur.getString (2));
                txtemail.setText (cur.getString (3));
            }
        }
        catch (Exception e){
            Toast.makeText (profil.this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
    }
    public  void deconnecter(View v){
        dt.id=null;
        Intent i=new Intent (profil.this,bottomnavigation.class);
        startActivity (i);
    }
    public void changerpassword(View v){
        Intent i=new Intent (profil.this,changer_password.class);
        startActivity (i);
    }
}
