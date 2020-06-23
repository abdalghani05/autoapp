package com.example.grandprojet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class modéleadapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<modéle> modéles;

    public modéleadapter(Context context, int layout, ArrayList<modéle> modéle) {
        this.context = context;
        this.layout = layout;
        this.modéles = modéle;
    }

    @Override
    public int getCount() {
        return modéles.size ();
    }

    @Override
    public Object getItem(int position) {
        return modéles.get (position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtnommarque, txtnommodule, txtvirsion, txtprix, txtannee, txtcarburateur;
        GridView gridView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        modéleadapter.ViewHolder holder = new modéleadapter.ViewHolder ();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate (layout, null);

            holder.txtnommarque = (TextView) row.findViewById (R.id.txtmarque);
            holder.txtnommodule = (TextView) row.findViewById (R.id.txtmodele);
            holder.txtvirsion = (TextView) row.findViewById (R.id.txtvirsion);
            holder.txtprix = (TextView) row.findViewById (R.id.txtprix);
            holder.txtannee = (TextView) row.findViewById (R.id.txtanne);
            holder.txtcarburateur = (TextView) row.findViewById (R.id.txtcarburater);
            holder.imageView = (ImageView) row.findViewById (R.id.img);
            holder.gridView=(GridView)row.findViewById (R.id.list1);
            row.setTag (holder);
        } else {
            holder = (modéleadapter.ViewHolder) row.getTag ();
        }
        modéle food = modéles.get(position);

        holder.txtnommarque.setText(food.getName_marque ());
        holder.txtnommodule.setText(food.getName_modele ());
        holder.txtvirsion.setText(food.getName_version ());
        holder.txtprix.setText(food.getPrix ());
        holder.txtannee.setText(food.getAnnée ());
        holder.txtcarburateur.setText(food.getCarburat ());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
