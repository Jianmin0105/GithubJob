package com.example.githubjob;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<JobItem> mDataSet;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView;
        }
    }

    public MyAdapter(List<JobItem> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
        ImageView logo = myViewHolder.textView.findViewById(R.id.logo);
        Picasso.get().load(mDataSet.get(i).getLogo()).error(R.drawable.no_photo).resize(150, 150).centerInside().into(logo);

        TextView textViewTitle = myViewHolder.textView.findViewById(R.id.textView_title);
        textViewTitle.setText(mDataSet.get(i).getTitle());
        TextView textViewCompany = myViewHolder.textView.findViewById(R.id.textView_company);
        textViewCompany.setText(mDataSet.get(i).getCompany());
        textViewCompany.append("\n");
        textViewCompany.append(mDataSet.get(i).getLocation());
//        TextView textViewLocation = myViewHolder.textView.findViewById(R.id.textView_location);
//        textViewLocation.setText(mDataSet.get(i).getLocation());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
