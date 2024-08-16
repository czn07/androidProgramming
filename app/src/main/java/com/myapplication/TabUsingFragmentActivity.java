package com.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TabUsingFragmentActivity extends AppCompatActivity {
    TextView tab1,tab2,tab3;
    ViewPager pager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_using_fragment);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        pager= findViewById(R.id.container);
        pager.setAdapter(new viewPagerAdapter(getSupportFragmentManager()));

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, new TopFragment())
//                .commit();
        tab1.setBackgroundColor(Color.GRAY);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab1.setBackgroundColor(Color.WHITE);
                tab2.setBackgroundColor(Color.WHITE);
                tab3.setBackgroundColor(Color.WHITE);
            if(position==0){
                tab1.setBackgroundColor(Color.GRAY);
            }else if(position==1){
                tab2.setBackgroundColor(Color.GRAY);
            }else{
                tab3.setBackgroundColor(Color.GRAY);
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void onTabClick(View view){
        tab1.setBackgroundColor(Color.WHITE);
        tab2.setBackgroundColor(Color.WHITE);
        tab3.setBackgroundColor(Color.WHITE);
        if(view.getId()==R.id.tab1){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container,new TopFragment())
//                    .commit();

            pager.setCurrentItem(0);

            tab1.setBackgroundColor(Color.GRAY);
        } else if(view.getId()==R.id.tab2){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container,new BottomFragment())
//                    .commit();

            pager.setCurrentItem(1);

            tab2.setBackgroundColor(Color.GRAY);
        }else{
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container,new TopFragment())
//                    .commit();

            pager.setCurrentItem(2);

            tab3.setBackgroundColor(Color.GRAY);
        }
    }

    public class viewPagerAdapter extends FragmentPagerAdapter{

        public viewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new TopFragment();
            }else if(position==1){
                return new BottomFragment();
            }else{
                return new TopFragment();
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
