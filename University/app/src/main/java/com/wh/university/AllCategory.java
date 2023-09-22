package com.wh.university;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


 

import java.util.ArrayList;
import java.util.List;

import adapter.AllCategoryAdapter;
import model.Category;


//做的分类：是点击查看更多后的选择菜单界面，目前只实现了返回按钮
public class AllCategory extends AppCompatActivity implements View.OnClickListener {

    RecyclerView AllCategoryRecycler;
    AllCategoryAdapter allCategoryAdapter;
    List<Category> CategoryList;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

        AllCategoryRecycler = findViewById(R.id.all_category);
         findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.imageView4).setOnClickListener(this);


        // adding data to model
        CategoryList = new ArrayList<>();
        CategoryList.add(new Category(1, R.drawable.ic_fruits));
        CategoryList.add(new Category(2, R.drawable.ic_veggies));
        CategoryList.add(new Category(3, R.drawable.ic_meat));
        CategoryList.add(new Category(4, R.drawable.ic_fish));
        CategoryList.add(new Category(5, R.drawable.ic_spices));
        CategoryList.add(new Category(6, R.drawable.ic_egg));
        CategoryList.add(new Category(7, R.drawable.ic_drink));
        CategoryList.add(new Category(8, R.drawable.ic_cookies));
        CategoryList.add(new Category(8, R.drawable.ic_juce));


        setCategoryRecycler(CategoryList);

    }

    private void setCategoryRecycler(List<Category> CategoryList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        AllCategoryRecycler.setLayoutManager(layoutManager);
        AllCategoryRecycler.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(16), true));
        AllCategoryRecycler.setItemAnimator(new DefaultItemAnimator());
        allCategoryAdapter = new AllCategoryAdapter(this,CategoryList);
        AllCategoryRecycler.setAdapter(allCategoryAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                i = new Intent(this, MainMenu.class);
                startActivity(i);
                break;
            case R.id.imageView4:
                i = new Intent(this, MainActivity.class);

                startActivity(i);
                break;
        }

    }

    // now we need some item decoration class for manage spacing

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}


