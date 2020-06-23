package com.example.grandprojet;

public class modéle {
    private int id;
    private  String nom_marque;
    private  String nom_modele;
    private  String nom_version;
    private String année;
    private  String carburat;
    private String prix;
    private  byte[] img;

    public modéle(int id,String nom_marque,String nom_modele,String nom_version,String année,String carburat,String prix,byte[] img){
        this.id=id;
        this.nom_marque=nom_marque;
        this.nom_modele=nom_modele;
        this.nom_version=nom_version;
        this.année=année;
        this.carburat=carburat;
        this.prix=prix;
        this.img=img;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //////////////////////////////////////////////////
    public String getName_marque() {
        return nom_marque;
    }
    public void setName_marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }
    ///////////////////////////////////////////////////////////
    public String getName_modele() {
        return nom_modele;
    }
    public void setName_modele(String nom_modele) {
        this.nom_modele = nom_modele;
    }
    ///////////////////////////////////////////////////////////
    public String getName_version() {
        return nom_version;
    }
    public void setName_version(String nom_version) {
        this.nom_version = nom_version;
    }
    ///////////////////////////////////////////////////////////
    public String getAnnée() {
        return année;
    }
    public void setAnnée(String année) {
        this.année = année;
    }
    ///////////////////////////////////////////////////////////
    public String getCarburat() {
        return carburat;
    }
    public void setCarburat(String carburat) {
        this.carburat = carburat;
    }
    ///////////////////////////////////////////////////////////
    public String getPrix() {
        return prix;
    }
    public void setPrix(String  prix) {
        this.prix = prix;
    }
    ///////////////////////////////////////////////////////////
    public byte[] getImage() {
        return img;
    }
    public void setImage(byte[] image) {
        this.img = image;
    }
}
