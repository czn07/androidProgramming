package com.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentExampleActivity extends AppCompatActivity {

    TopFragment topFragment;
    BottomFragment bottomFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_example_layout);
        topFragment = new TopFragment();
        bottomFragment = new BottomFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.topContainer, topFragment);
        transaction.replace(R.id.bottomContainer, bottomFragment);
        transaction.commit();
    }

    public void setBottomFragmentTitle(String value){
        bottomFragment.setTitle(value);
    }
}
