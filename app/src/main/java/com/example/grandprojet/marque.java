package com.example.grandprojet;

public class marque {
         private int id;
         private  String nom;
         private  byte[] img;

         public marque(int id,String nom,byte[] img){
             this.id=id;
             this.nom=nom;
             this.img=img;
         }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String name) {
        this.nom = nom;
    }
    public byte[] getImage() {
        return img;
    }

    public void setImage(byte[] image) {
        this.img = image;
    }
}
