package com.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class RegisterActivity extends AppCompatActivity {
    EditText firstname, email, password, confirm, username ;
    SharedPreferences sharedPreferences;

    //database object
    DatabaseHelper databaseHelper;
    int id;
    ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        //to compare id from DetailActivity or LoginActivity
        id= getIntent().getIntExtra("id",0);


        databaseHelper = new DatabaseHelper(this);

        sharedPreferences = getSharedPreferences("Userinfo",0);
        firstname  = findViewById(R.id.firstname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);

        //condition to show the previous data for updating
        if(id>0){
            Userinfo info = databaseHelper.getUserInfo(id+"");
            firstname.setText(info.firstname);
            email.setText(info.email);
//          username.setText(info.username);
            password.setText(info.password);
            confirm.setText("Update");

        }

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                String firstnameValue = firstname.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String confirmValue = confirm.getText().toString();

                //validation method call
                if (emailValidation(email) && emptyFieldValidation(password)){


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstname", firstnameValue);
                editor.putString("email", emailValue);
                editor.putString("password", passwordValue);
                editor.putString("confirm", confirmValue);
                editor.apply();


                //putting data into ContentValue class
                    ContentValues values = new ContentValues();
                    values.put("firstname", firstnameValue);
                    values.put("email", emailValue);
                    values.put("password", passwordValue);
                    values.put("username", firstnameValue);
                    if(bitmap!=null)
                    values.put("image",getByteArray(bitmap));
                    //condition to login or update
                    if (id==0) {
                        databaseHelper.insertUser(values);
                        Toast.makeText(RegisterActivity.this, "info saved", Toast.LENGTH_LONG).show();
                    }else{
                        databaseHelper.updateUser(String.valueOf(id),values);
                        Toast.makeText(RegisterActivity.this, "info updated", Toast.LENGTH_LONG).show();
                        finish();
                    }



                }
            }
        });

       image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivityForResult(intent,101);
            }
        });
    }

    Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);

        }
    }

    //validation
    public boolean emptyFieldValidation(EditText view){
        String value = view.getText().toString();
        if (value.length()>0){
            return true;
        }
        view.setError("Enter Details");
        return false;
    }
    public boolean emailValidation(EditText view){
        String value = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            return true;
        }
        view.setError("Invalid Email Address");
        return false;
    }
    public static byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}


