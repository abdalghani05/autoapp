package com.example.grandprojet;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class database extends SQLiteAssetHelper {
    private static final String DATABASE_NaME="voiture.db";
    private static final int DATABASE_VERSION=1;
    public static String id=null;
    public  static  String nommarque=null;
    public  static  String idmodele=null;
    public  static  String cherchemarque="Tous";
    public  static  String cherchemodele="Tous";
    public  static  String chercheversion="Tous";
    public  static  String chercheannee="Tous";
    public static int reche=0;



    public database(Context context){
        super(context,DATABASE_NaME,null,DATABASE_VERSION);
    }
}
