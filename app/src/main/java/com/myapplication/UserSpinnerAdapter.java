package com.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserSpinnerAdapter extends ArrayAdapter<Userinfo> {
    Context context;

    public UserSpinnerAdapter(@NonNull Context context, ArrayList<Userinfo>list ) {
        super(context,0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_spinner,null);
        ImageView image = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.name);
        Userinfo info = getItem(position);
        name.setText(info.firstname);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_spinner,null);
        ImageView image = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.name);
        Userinfo info = getItem(position);
        name.setText(info.firstname);
        return view;
    }
}
