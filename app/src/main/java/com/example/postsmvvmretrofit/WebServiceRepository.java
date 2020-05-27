package com.example.postsmvvmretrofit;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.postsmvvmretrofit.model.ResultModel;
import com.example.postsmvvmretrofit.retrofit.ApiInterface;
import com.example.postsmvvmretrofit.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceRepository {
    private Application application;

    List<ResultModel> webserviceResponseList = new ArrayList<>();

    final MutableLiveData<List<ResultModel>> data = new MutableLiveData<>();

    public WebServiceRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<ResultModel>> providesWebService(){
        ApiInterface apiInterface = RetrofitClient.getService();

        Call<List<ResultModel>> call = apiInterface.getPosts();

        call.enqueue(new Callback<List<ResultModel>>() {
            @Override
            public void onResponse(Call<List<ResultModel>> call, Response<List<ResultModel>> response) {
                if (response.isSuccessful()){
                    webserviceResponseList = response.body();

                    //insert a list of posts in room database
                    PostRoomDBRepository postRoomDBRepository = new PostRoomDBRepository(application);
                    postRoomDBRepository.insertPosts(webserviceResponseList);

                    data.setValue(webserviceResponseList);
                }
            }

            @Override
            public void onFailure(Call<List<ResultModel>> call, Throwable t) {

            }
        });

        return data;

    }




}
