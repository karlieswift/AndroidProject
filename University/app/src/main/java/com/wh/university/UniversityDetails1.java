package com.wh.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.UniversityLines;
import utils.GetLines;
import utils.UniversityDBManager;

public class UniversityDetails1 extends AppCompatActivity {

    private LinearLayout ll_university_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_detail2);
        ll_university_list = findViewById(R.id.ll_university_list2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        UniversityLines universityLinesbyName = UniversityDBManager.getInstance(this).getUniversityLinesbyName(name);
        ArrayList<ArrayList<String>> lines = GetLines.getLines(universityLinesbyName.getWenke());

        for (ArrayList<String> line : lines) {
            if (line.get(0).equals("")){
                continue;
            }
            for (String s : line) {
                View view = LayoutInflater.from(this).inflate(R.layout.university_special_score, null);
                String[] split = s.toString().split("-");
                Log.d("tag",split[0]);
                TextView tv_university_special_name = view.findViewById(R.id.tv_university_special_name);
                TextView tv_university_special_score_rank = view.findViewById(R.id.tv_university_special_score_rank);
                TextView tv_university_special_average_score = view.findViewById(R.id.tv_university_special_average_score);
                tv_university_special_name.setText(split[0]);
                tv_university_special_average_score.setText(split[1]);
                tv_university_special_score_rank.setText(split[2]+"/"+split[3]);
                ll_university_list.addView(view);
            }

        }


    }

    }