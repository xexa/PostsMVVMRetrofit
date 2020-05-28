package com.example.postsmvvmretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsmvvmretrofit.R;
import com.example.postsmvvmretrofit.model.ResultModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewholder> {
    private List<ResultModel> resultModels;

    public PostAdapter(List<ResultModel> resultModels) {
        this.resultModels = resultModels;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.title.setText(resultModels.get(position).getTitle());
        holder.body.setText(resultModels.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        if (resultModels.size() != 0)
            return resultModels.size();
        return 0;
    }

    public void setResultModels(List<ResultModel> resultModelList) {
        this.resultModels = resultModelList;
        notifyDataSetChanged();
    }

    class MyViewholder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView body;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text_view);
            body = itemView.findViewById(R.id.body_text_view);
        }
    }
}
