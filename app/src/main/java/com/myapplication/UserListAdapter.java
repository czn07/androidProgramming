package com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserListAdapter extends ArrayAdapter<Userinfo> {

    Context context;

    public UserListAdapter(@NonNull Context context, ArrayList<Userinfo> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        ImageView image= view.findViewById(R.id.image);

        Userinfo info = getItem(position);
        //if(image!=null){
            //image.setImageBitmap(RegisterActivity.getBitmap(info.image));}
        name.setText(info.firstname);
        email.setText(info.email);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("id",info.id);
                context.startActivity(intent);

            }
        });
        return view;
    }
}
