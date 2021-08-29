package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBarLayout;
    TabLayout tablayout;
    ViewPager viewPager;
    ViewPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appBarLayout = findViewById(R.id.appbar);
        viewPager = findViewById(R.id.viewpager_id);
        tablayout = findViewById(R.id.tablayout);


        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.AddFragment(new LivingRoom(), "Living Room");
        pagerAdapter.AddFragment(new BedRoomTab(), "Bed Room");
        pagerAdapter.AddFragment(new KitchenTab(), "Kitchen");
        pagerAdapter.AddFragment(new BathRoomTab(), "Bath Room");
        pagerAdapter.AddFragment(new ToiletTab(), "Toilet");
        pagerAdapter.AddFragment(new CorridorTab(), "Corridor");
        pagerAdapter.AddFragment(new CameraTab(), "Camera");
        pagerAdapter.AddFragment(new SettingTab(), "Status");


        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);


    }

}
