package com.wh.university;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;


import com.google.android.material.tabs.TabLayout;

import adapter.VPAdapter;
import model.UniversityLines;
import utils.UniversityDBManager;

public class UniversityDetails extends AppCompatActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;



    private LinearLayout ll_university_list;
    private BlankFragment_li fragment1;
    private BlankFragment_li fragment2;
    private BlankFragment_wen fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_details);

        fragment1 = new BlankFragment_li();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        UniversityLines universityLinesbyName = UniversityDBManager.getInstance(this).getUniversityLinesbyName(name);


        Bundle bundleli_1 = new Bundle();
        bundleli_1.putString("lines",universityLinesbyName.getLike());
        fragment1.setArguments(bundleli_1);




        fragment2 = new BlankFragment_li();
        Bundle bundle = new Bundle();
        bundle.putString("lines",universityLinesbyName.getLike());
        fragment2.setArguments(bundle);




        fragment3 = new BlankFragment_wen();
        Bundle bundle_wen = new Bundle();
        bundle_wen.putString("lines",universityLinesbyName.getWenke());
        fragment3.setArguments(bundle_wen);



        tableLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpage);
        tableLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(fragment1,"简介");
        vpAdapter.addFragment(fragment2,"理科");
        vpAdapter.addFragment(fragment3,"文科");
        viewPager.setAdapter(vpAdapter);


    }


}