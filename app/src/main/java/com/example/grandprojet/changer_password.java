package com.example.grandprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class changer_password extends AppCompatActivity {
    database dt;//class
    SQLiteDatabase db;//database bache dir les requte dyal sql
    TextInputLayout anciennemotdepaase,motdepasse1,motdepasse2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_changer_password);

        anciennemotdepaase=(TextInputLayout)findViewById (R.id.anciennepass);
        motdepasse1=(TextInputLayout)findViewById (R.id.inputpasword);
        motdepasse2=(TextInputLayout)findViewById (R.id.inputpasword2);
    }
    public void changer(View v){
        try {
                dt=new database (this);
                db=dt.getReadableDatabase ();
                Cursor cur=db.rawQuery ("select * from client where idcli=? and motdepasse=?",new String[] {dt.id.toString (),anciennemotdepaase.getEditText ().getText ().toString ()});
                if(cur.getCount ()!=0  ){
                    cur.moveToFirst ();
                    if(motdepasse1.getEditText ().getText ().toString ().equals (motdepasse2.getEditText ().getText ().toString ())){
                    dt=new database (this);
                    db=dt.getWritableDatabase ();
                    db.execSQL ("update client set motdepasse=? where idcli=?",new String[] {motdepasse1.getEditText ().getText ().toString (),dt.id.toString ()});
                        Toast.makeText (this,"Le mot de passe est changé",Toast.LENGTH_LONG).show ();
                    }
                    else {
                        Toast.makeText (this,"Les mots de passe ne correspondent pas",Toast.LENGTH_LONG).show ();
                    }
                }
                else {
                    Toast.makeText (this,"Ancienne mot de passe invalide ",Toast.LENGTH_LONG).show ();
                }
        }
        catch (Exception e){
            Toast.makeText (changer_password.this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
    }
}
