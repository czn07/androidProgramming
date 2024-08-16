package com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login, register;
    CheckBox remember_me;
    TextView forgot_password;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferences = getSharedPreferences("Userinfo",0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        remember_me = findViewById(R.id.remember_me);
        forgot_password = findViewById(R.id.forgot_password);

        if (sharedPreferences.getBoolean("remember_me",false)){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();


                String registeredUsername = sharedPreferences.getString("email","");
                String registeredPassword = sharedPreferences.getString("password","");
                if(usernameValue.equals(registeredUsername) &&
                        passwordValue.equals(registeredPassword)){
                    if (remember_me.isChecked()){
                        sharedPreferences.edit().putBoolean("remember_me",true).commit();
                    }
                    Toast.makeText(LoginActivity.this,"Welcome "+registeredUsername,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }else
                    Toast.makeText(LoginActivity.this,"Invalid Username or Password:",Toast.LENGTH_LONG).show();



            }
        });


    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    });

    }
}
