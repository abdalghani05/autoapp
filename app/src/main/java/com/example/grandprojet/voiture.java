package com.example.grandprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class voiture extends AppCompatActivity {

    database dt;//class dyal database
    SQLiteDatabase db; /////bache katkhadam les requete sql
    ImageView img;
    TextView marque,modele,versionm,prix,annee,carburat,pf,pr,bv,nr,vm,cv,cr,cm,co2,cat,ndr,coff;
    SliderLayout sliderLayout;
    ImageButton imgfav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_voiture);

        ///////////////////////////// hadi bache ta9dar tarja3 li kata3atik saham
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);


        ////////////////////slider howa li fihe les photo
        sliderLayout=(SliderLayout) findViewById (R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec (1);
        setSliderView ();


        //////////////////les textviex li kaynine
        marque=(TextView)findViewById (R.id.txtmarque);
        modele=(TextView)findViewById (R.id.txtmodéle);
        versionm=(TextView)findViewById (R.id.txtversion);
        prix=(TextView)findViewById (R.id.txtprix);
        annee=(TextView)findViewById (R.id.txtanne);
        carburat=(TextView)findViewById (R.id.txtcarburant);
        pf=(TextView)findViewById (R.id.txtPuissance_fiscale);
        pr=(TextView)findViewById (R.id.txtPuissance_réelle);
        bv=(TextView)findViewById (R.id.txtBoite_de_vitesses);
        nr=(TextView)findViewById (R.id.txtNombre_de_rapports);
        vm=(TextView)findViewById (R.id.txtVitesse_maxi);
        cv=(TextView)findViewById (R.id.txtConso_ville);
        cr=(TextView)findViewById (R.id.txtConso_route);
        cm=(TextView)findViewById (R.id.txtConso_mixte);
        co2=(TextView)findViewById (R.id.txtEmission_CO2);
        cat=(TextView)findViewById (R.id.txtCatégorie);
        ndr=(TextView)findViewById (R.id.txtNombre_de_portes);
        coff=(TextView)findViewById (R.id.txtxCoffre);
        imgfav=(ImageButton)findViewById (R.id.btnfav);



        try {

            dt=new database (this);
            db=dt.getReadableDatabase ();
            Cursor cur=db.rawQuery ("select Nom,nom_modéle,nom_version,prix,année,carburant,puissance_fiscale,puissance_réelle,boite_de_vitesse,nombre_de_rapports from modéle m,marque ma where m.id=ma.id and m.idmodul=?",new String[] {dt.idmodele.toString ()});
            Cursor cur1=db.rawQuery ("select vitesse_maxi,conso_ville,conso_route,conso_mixte,emission_co2,catégorie,nombre_de_portes,coffre from modéle m,marque ma where m.id=ma.id and m.idmodul=?",new String[] {dt.idmodele.toString ()});
            if(cur.getCount ()!=0  ){
                cur.moveToFirst ();
                cur1.moveToFirst ();
                marque.setText (cur.getString (0));
                modele.setText (cur.getString (1));
                versionm.setText (cur.getString (2));
                prix.setText (cur.getInt (3)+" DH");
                annee.setText (cur.getString (4));
                carburat.setText (cur.getString (5));
                pf.setText (cur.getString (6));
                pr.setText (cur.getString (7));
                bv.setText (cur.getString (8));
                nr.setText (cur.getString (9));

                vm.setText (cur1.getString (0));
                cv.setText (cur1.getString (1));
                cr.setText (cur1.getString (2));
                cm.setText (cur1.getString (3));
                co2.setText (cur1.getString (4));
                cat.setText (cur1.getString (5));
                ndr.setText (cur1.getString (6));
                coff.setText (cur1.getString (7));
            }

            ///////////////////historique///////////////////////////////
            if (dt.id!=null){
                dt=new database (this);
                db=dt.getReadableDatabase ();
                Cursor cur5=db.rawQuery ("select * from historique where idcli=? and idmodul=?",new String[] {dt.id.toString (),dt.idmodele.toString ()});
                if(cur5.getCount ()==0){
                dt=new database (this);
                db=dt.getWritableDatabase ();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                db.execSQL ("insert into historique values(?,?,?);"
                        ,new String[] {dt.idmodele.toString (),dt.id.toString (),dateFormat.format(date).toString ()});
                }
                else {
                    dt=new database (this);
                    db=dt.getWritableDatabase ();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    db.execSQL ("update historique set datehis=? where idcli=? and idmodul=?;"
                            ,new String[] {dateFormat.format(date).toString (),dt.id.toString (),dt.idmodele.toString ()});
                }
            }
            //////////////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////favouris//////////////////////////////////////////////////////////
            if (dt.id!=null){
                try {
                    dt=new database (this);
                    db=dt.getReadableDatabase ();
                    Cursor cur5=db.rawQuery ("select * from favouris where idcli=? and idmodul=?",new String[] {dt.id.toString (),dt.idmodele.toString ()});
                    if(cur5.getCount ()==0){
                        imgfav.setBackgroundResource (R.drawable.ic_favorite_border_black_24dp);
                        imgfav.setSelected(!imgfav.isSelected());
                    }
                    else {
                        imgfav.setBackgroundResource (R.drawable.ic_favorite_black_24dp);
                        imgfav.setSelected(imgfav.isSelected());
                    }
                    imgfav.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            if (imgfav.isSelected()) {
                                imgfav.setBackgroundResource (R.drawable.ic_favorite_black_24dp);
                                db=dt.getWritableDatabase ();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                Date date = new Date();
                                db.execSQL ("insert into favouris values(?,?,?);"
                                        ,new String[] {dt.idmodele.toString (),dt.id.toString (),dateFormat.format(date).toString ()});
                            }
                            else {
                                imgfav.setBackgroundResource (R.drawable.ic_favorite_border_black_24dp);
                                db=dt.getWritableDatabase ();
                                Date date = new Date();
                                db.execSQL ("delete from favouris where idmodul=? and idcli=?;"
                                        ,new String[] {dt.idmodele.toString (),dt.id.toString ()});
                            }
                        }
                    });
                }
                catch (Exception e){
                    Toast.makeText (voiture.this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        catch (Exception e){
            Toast.makeText (voiture.this,e.getMessage ().toString (),Toast.LENGTH_LONG).show ();
        }
    }

    /////////////////////////////////slider photo /////////////////////////////////////////////////////////////////////
    private void setSliderView(){
        dt=new database (this);
        db=dt.getReadableDatabase ();

        final Cursor cur2=db.rawQuery ("select m.photo,m.photo2,m.photo3,m.photo4 from modéle m,marque ma where m.id=ma.id and m.idmodul=?",new String[] {dt.idmodele.toString ()});
        cur2.moveToFirst ();
        for (int i=0;i<=3;i++){
            DefaultSliderView sliderView=new DefaultSliderView (this);
            switch (i){
                case 0: sliderView.setImageByte (cur2.getBlob (0));break;
                case 1: sliderView.setImageByte (cur2.getBlob (1));break;
                case 2: sliderView.setImageByte (cur2.getBlob (2));break;
                case 3: sliderView.setImageByte (cur2.getBlob (3));break;
            }
            sliderView.setImageScaleType (ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView (sliderView);
        }
    }
}
