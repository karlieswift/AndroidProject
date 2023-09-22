package com.wh.university;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import static com.wh.university.R.drawable.ic_home_fish;
import static com.wh.university.R.drawable.ic_home_fruits;
import static com.wh.university.R.drawable.ic_home_meats;
import static com.wh.university.R.drawable.ic_home_veggies;
import adapter.CategoryAdapter;
import model.Category;
import model.UniversityInfo;
import utils.CopyDbFromAsset;
import utils.UniversityDBManager;

//        String directory=getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()+ File.separatorChar;
//入口界面
public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    RecyclerView  categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    private Intent i;
    private LinearLayout ll_university_list;
    List<UniversityInfo> recentlyViewedList=new ArrayList<>();


    private TextView tv_item_university_name;
    private  TextView tv_item_university_location;
    private TextView tv_item_university_rank_and_score;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        SharedPreferences pre = getSharedPreferences("name",MODE_PRIVATE);
        Boolean flag = pre.getBoolean("flag", true);
        CopyDbFromAsset.copyDbFromAssetToMySelf(getApplicationContext(), "UniversityInfo.db");
        if(flag == false){
            Toast.makeText(this,"getSharedPreferences里面",Toast.LENGTH_SHORT).show();
        }

        categoryRecyclerView = findViewById(R.id.universitydiscountedRecycler);
        findViewById(R.id.selectCategories).setOnClickListener(this);
        EditText searchEditText=findViewById(R.id.key_search) ;
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    String s = searchEditText.getText().toString();
                    Intent i = new Intent(MainMenu.this, NewUniversityListActivitty.class);
                    i.putExtra("name",s);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, ic_home_fruits));
        categoryList.add(new Category(2, ic_home_fish));
        categoryList.add(new Category(3, ic_home_meats));
        categoryList.add(new Category(4, ic_home_veggies));
        categoryList.add(new Category(5, ic_home_fruits));
        categoryList.add(new Category(6, ic_home_fish));
        categoryList.add(new Category(7, ic_home_meats));
        categoryList.add(new Category(8, ic_home_veggies));
        setCategoryRecycler(categoryList);











        ll_university_list = findViewById(R.id.ll_university_list11);

        recentlyViewedList.add(new UniversityInfo("清华大学","综合类","北京","北京市","678","1"));
        recentlyViewedList.add(new UniversityInfo("北京大学","综合类","北京","北京市","677","2"));
        recentlyViewedList.add(new UniversityInfo("浙江大学","综合类","浙江","杭州市","644","3"));
        recentlyViewedList.add(new UniversityInfo("上海交通大学","综合类","上海","上海市","652","4"));
        recentlyViewedList.add(new UniversityInfo("复旦大学","综合类","上海","上海市","661","5"));
        recentlyViewedList.add(new UniversityInfo("南京大学","综合类","江苏","南京市","656","6"));


//        recentlyViewedList= UniversityDBManager.getInstance(this).queryPopularByCity("北京市");
        for (UniversityInfo universityInfo : recentlyViewedList) {
            View view = LayoutInflater.from(this).inflate(R.layout.university_item, null);
            tv_item_university_name = view.findViewById(R.id.tv_item_university_name);
            tv_item_university_location = view.findViewById(R.id.tv_item_university_location);
            tv_item_university_rank_and_score = view.findViewById(R.id.tv_item_university_rank_and_score);


            tv_item_university_name.setText(universityInfo.getName());
            tv_item_university_location.setText(universityInfo.getProvince()+" · "+universityInfo.getCity());


            String rank_score= universityInfo.getScore_line2021()+"分 | "+universityInfo.getSoft_science_ranking()+"位";
            tv_item_university_rank_and_score.setText(rank_score);


            view.setOnClickListener(v->{
//                Intent intent = new Intent(MainActivity.this, UniversityDetails1.class);
                Intent intent = new Intent(MainMenu.this, UniversityDetails.class);
                intent.putExtra("name",universityInfo.getName());
                startActivity(intent);
            });

            ll_university_list.addView(view);

        }





















    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.selectCategories:
                i = new Intent(MainMenu.this, AllCategory.class);
                startActivity(i);
                break;
        }

    }
}