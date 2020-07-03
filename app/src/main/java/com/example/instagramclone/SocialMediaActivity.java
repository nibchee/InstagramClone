package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;


public class SocialMediaActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("Social Media App");

        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.viewPager);
    tabAdapter=new TabAdapter(getSupportFragmentManager());
    viewPager.setAdapter(tabAdapter);

    tableLayout=(TabLayout) findViewById(R.id.tabLayout);
    tableLayout.setupWithViewPager(viewPager,false);

    }
}