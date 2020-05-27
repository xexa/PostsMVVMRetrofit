package com.example.postsmvvmretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.postsmvvmretrofit.PostRoomDBRepository;
import com.example.postsmvvmretrofit.WebServiceRepository;
import com.example.postsmvvmretrofit.model.ResultModel;

import java.util.List;

public class PostsListViewModel extends AndroidViewModel {

    private PostRoomDBRepository postRoomDBRepository;
    private LiveData<List<ResultModel>>  allPosts;
    private WebServiceRepository webServiceRepository;
    private LiveData<List<ResultModel>>  retroObservable;

    public PostsListViewModel(@NonNull Application application) {
        super(application);
        postRoomDBRepository = new PostRoomDBRepository(application);
        webServiceRepository = new WebServiceRepository(application);

        retroObservable = webServiceRepository.providesWebService();

        //postRoomDBRepository.insertPosts(retroObservable.getValue());
        allPosts = postRoomDBRepository.getAllPosts();
    }

    public LiveData<List<ResultModel>> getAllPosts(){
        return allPosts;
    }
}
