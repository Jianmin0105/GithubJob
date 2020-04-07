package com.example.githubjob;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataSet;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView;
        }
    }

    public MyAdapter(String[] mDataSet) {
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
        TextView textView = myViewHolder.textView.findViewById(R.id.textView);
        textView.setText(mDataSet[i]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
