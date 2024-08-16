package com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        Log.i("Lifecycle","onCreate");

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSharedPreferences("Userinfo",0).edit().putBoolean("remember_me",false).commit();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    //showing menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //adding action on menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu1){
            startActivity(new Intent(this,UserListActivity.class));
            Toast.makeText(this, "UserListActivity", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.menu2) {
           startActivity(new Intent(this, UserListViewActivity.class));
            Toast.makeText(this, "UserListViewActivity", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.menu3){
            startActivity(new Intent(this,RegisterActivity.class));
            Toast.makeText(this, "RegisterActivity", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.submenu1) {
            startActivity(new Intent(this, FragmentExampleActivity.class));
            Toast.makeText(this, "FragmentExampleActivity", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.submenu2){
            startActivity(new Intent(this,TabUsingFragmentActivity.class));
            Toast.makeText(this,"TabUsingFragmentActivity",Toast.LENGTH_SHORT).show();
        }else if (id == R.id.logout){
            getSharedPreferences("Userinfo",0).edit().putBoolean("remember_me",false).apply();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
