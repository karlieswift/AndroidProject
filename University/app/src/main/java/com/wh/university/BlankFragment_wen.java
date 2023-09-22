package com.wh.university;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import utils.GetLines;

public class BlankFragment_wen extends Fragment {

    private LinearLayout ll_university_list;
    Bundle bundel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view1= inflater.inflate(R.layout.fragment_blank_wen, container, false);
        ll_university_list = view1.findViewById(R.id.ll_university_list_wen);
        bundel=this.getArguments();

        String lines = bundel.getString("lines");




        ArrayList<ArrayList<String>> fraglines = GetLines.getLines(lines);


        for (ArrayList<String> line : fraglines) {
            if (line.get(0).equals("")){
                continue;
            }
            for (String s : line) {
                View view = inflater.inflate(R.layout.university_special_score, null);
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




        return view1;
    }
}