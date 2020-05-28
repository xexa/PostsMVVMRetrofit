package com.example.postsmvvmretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.postsmvvmretrofit.R;
import com.example.postsmvvmretrofit.adapter.PostAdapter;
import com.example.postsmvvmretrofit.model.ResultModel;
import com.example.postsmvvmretrofit.viewmodel.PostsListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private PostsListViewModel viewModel;
    private List<ResultModel> resultModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(resultModels);
        recyclerView.setAdapter(postAdapter);

        viewModel = new ViewModelProvider(this).get(PostsListViewModel.class);

        viewModel.getAllPosts().observe(this, new Observer<List<ResultModel>>() {
            @Override
            public void onChanged(List<ResultModel> resultModels) {
                postAdapter.setResultModels(resultModels);
            }
        });

    }
}
