package com.example.grandprojet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class marqueadabter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<marque> marques;

    public marqueadabter(Context context, int layout, ArrayList<marque> marques) {
        this.context = context;
        this.layout = layout;
        this.marques = marques;
    }
    @Override
    public int getCount() {
        return marques.size ();
    }

    @Override
    public Object getItem(int position) {
        return marques.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        ImageView imageView;
        TextView txtName;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txt);
            holder.imageView = (ImageView) row.findViewById(R.id.img);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        marque food = marques.get(position);

        holder.txtName.setText(food.getName());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
