package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wh.university.R;
import com.wh.university.UniversityDetails;
import com.wh.university.UniversityDetails1;

import java.util.List;

import model.UniversityInfo;

public class UniversityRecyclerViewAdapter extends RecyclerView.Adapter<UniversityRecyclerViewAdapter.UniversityRecyclerViewHolder> {

    Context context;
    List<UniversityInfo> universityInfoList;

    public UniversityRecyclerViewAdapter(Context context, List<UniversityInfo> universityInfoList) {
        this.context=context;
        this.universityInfoList=universityInfoList;
    }

    @NonNull
    @Override
    public UniversityRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item1, parent, false);
        return new UniversityRecyclerViewAdapter.UniversityRecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UniversityRecyclerViewHolder holder, int position) {
        UniversityInfo uniInfo = universityInfoList.get(position);
        holder.universityImage.setImageResource(R.drawable.gilrs);
        holder.tv_university_name.setText(uniInfo.getName());

        String desc= uniInfo.getProvince()+" · "+uniInfo.getCity()+" | "+uniInfo.getSchool_type()+" | "+uniInfo.getSchool_content();
        holder.tv_university_desc.setText(desc);
        String line_score=uniInfo.getScore_line2021();

        if ("".equals(line_score)){
            line_score="0：0";
        }

        String rank_score= line_score.split("：")[1]+"分 | "+uniInfo.getSoft_science_ranking()+"位";
        holder.tv_university_rank_and_score.setText(rank_score);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, UniversityDetails.class);
                i.putExtra("name",uniInfo.getName());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return universityInfoList.size();
    }
    public static class UniversityRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView universityImage;
        TextView tv_university_name,tv_university_desc,tv_university_rank_and_score;
        public UniversityRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            universityImage = itemView.findViewById(R.id.university_image);
            tv_university_name = itemView.findViewById(R.id.tv_university_name);
            tv_university_desc = itemView.findViewById(R.id.tv_university_desc);
            tv_university_rank_and_score = itemView.findViewById(R.id.tv_university_rank_and_score);


        }
    }
}
