package com.example.vespaair;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List  <ExamItem>  examItemList;

    public  MyAdapter(List<ExamItem> examItemList) {
        this.examItemList = examItemList;

    }

    @NonNull
    @Override

    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExamItem examItem = examItemList.get(position);

        holder.examName.setText(examItem.getName());
        holder.examDate.setText(examItem.getDate());
        holder.examMessage.setText(examItem.getMessage());
        holder.examPic.setImageResource(examItem.getImage1());
        holder.examPic2.setImageResource(examItem.getImage2());
    }

    @Override
    public int getItemCount() {
        return examItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView examName;
        TextView examDate;
        TextView examMessage;
        ImageView examPic;
        ImageView examPic2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            examName = itemView.findViewById(R.id.examName);
            examDate = itemView.findViewById(R.id.examDate);
            examMessage = itemView.findViewById(R.id.examMessage);
            examPic = itemView.findViewById(R.id.examImage1);
            examPic2 = itemView.findViewById(R.id.examImage2);
        }
    }

}
