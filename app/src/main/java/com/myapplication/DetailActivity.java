package com.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    String id;
    DatabaseHelper databaseHelper;
    TextView name, email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        id = getIntent().getStringExtra("id");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        databaseHelper = new DatabaseHelper(this);
        /*to show the values from database
            move inside onResume() method
        Userinfo info = databaseHelper.getUserInfo(id);
        name.setText(info.firstname);
        email.setText(info.email);
        password.setText(info.password);*/

        //calling delete and update methods
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showing alertbox before deleting info
                showDeleteAlertDialog();
            }
        });

        findViewById(R.id.uddate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });
    }

    //to show updated data
    @Override
    protected void onResume() {
        super.onResume();
        Userinfo info = databaseHelper.getUserInfo(id);
        name.setText(info.firstname);
        email.setText(info.email);
        password.setText(info.password);
    }

    public void showDeleteAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Are You Sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteUser(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();
    }

}
