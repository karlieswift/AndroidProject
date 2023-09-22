package com.wh.university;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;


import model.UniversityInfo;
import utils.UniversityDBManager;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_university_list;
    List<UniversityInfo> recentlyViewedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ll_university_list = findViewById(R.id.ll_university_list1);
        recentlyViewedList= UniversityDBManager.getInstance(this).queryByCity("北京市");

        for (UniversityInfo universityInfo : recentlyViewedList) {
            View view = LayoutInflater.from(this).inflate(R.layout.university_item1, null);
            TextView tv_item_university_name = view.findViewById(R.id.tv_item_university_name1);
            TextView tv_item_university_location = view.findViewById(R.id.tv_item_university_location1);
            TextView tv_item_university_rank_and_score = view.findViewById(R.id.tv_item_university_rank_and_score1);
            tv_item_university_name.setText(universityInfo.getName());
            tv_item_university_location.setText(universityInfo.getProvince()+" · "+universityInfo.getCity());

            String line_score=universityInfo.getScore_line2021();
            if ("".equals(line_score)){
                line_score="0：0";
            }
            String rank_score= line_score.split("：")[1]+"分 | "+universityInfo.getSoft_science_ranking()+"位";
            tv_item_university_rank_and_score.setText(rank_score);
            view.setOnClickListener(this);
            ll_university_list.addView(view);
        }
        for (int i=0;i<2;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.university_item1, null);
            ll_university_list.addView(view);
        }

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity4.this, UniversityDetails1.class);
        startActivity(intent);
    }
}