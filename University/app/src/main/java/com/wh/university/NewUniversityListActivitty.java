package com.wh.university;




import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.wh.university.R.drawable.*;



import adapter.UniversityRecyclerViewAdapter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import model.UniversityInfo;
import utils.UniversityDBManager;


//这个是通过关键字查询的结果界面
public class NewUniversityListActivitty extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recentlyViewedRecycler;


    UniversityRecyclerViewAdapter recentlyViewedAdapter;
    List<UniversityInfo> recentlyViewedList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_university_list_activitty);

        findViewById(R.id.NewUniversityListtextView).setOnClickListener(this);

        recentlyViewedRecycler = findViewById(R.id.NewUniversityListrecently_item);


//        allCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(UniversityListActivitty.this, ProductDetails.class);
//                startActivity(i);
//            }
//        });

        Intent i = getIntent();


        String name = i.getStringExtra("name");
        if(name==null){
            recentlyViewedList= UniversityDBManager.getInstance(this).queryByName("济南市");
        }

        recentlyViewedList= UniversityDBManager.getInstance(this).queryByName(name);

        setRecentlyViewedRecycler(recentlyViewedList);

//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                recentlyViewedAdapter.getFilter().filter(charSequence);
//                search = charSequence;
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

    }


    private void setRecentlyViewedRecycler(List<UniversityInfo> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new UniversityRecyclerViewAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.NewUniversityListtextView:
                finish();
                break;
        }
    }
}
