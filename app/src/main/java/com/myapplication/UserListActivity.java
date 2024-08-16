package com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_layout);
        databaseHelper = new DatabaseHelper(this);
        container= findViewById(R.id.container);
        displayUsers();
    }

    public void displayUsers(){
        ArrayList<Userinfo>list= databaseHelper.getUserList();
        for (Userinfo info:list) {
            TextView textView= new TextView(UserListActivity.this);
            textView.setText(info.firstname+" "+info.username+" "+info.email);

            //to show info from database in layout view
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout,null);
            TextView name = view.findViewById(R.id.name);
            TextView email = view.findViewById(R.id.email);
            ImageView image= view.findViewById(R.id.image);

            /*if(image!=null)
            image.setImageBitmap(RegisterActivity.getBitmap(info.image));}*/
            name.setText(info.firstname);
            email.setText(info.email);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserListActivity.this,DetailActivity.class);
                    intent.putExtra("id",info.id);
                    startActivity(intent);

                }
        });
        container.addView(view);




        }
    }
}

