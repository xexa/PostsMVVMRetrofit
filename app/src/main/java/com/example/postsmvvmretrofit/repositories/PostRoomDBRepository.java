package com.example.postsmvvmretrofit.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.postsmvvmretrofit.database.PostInfoDao;
import com.example.postsmvvmretrofit.database.PostsInfoRoomDatabase;
import com.example.postsmvvmretrofit.model.ResultModel;

import java.util.List;

public class PostRoomDBRepository {

    private PostInfoDao postInfoDao;
    private Application application;
    LiveData<List<ResultModel>> allPosts;

    public PostRoomDBRepository(Application application) {
        PostsInfoRoomDatabase db = PostsInfoRoomDatabase.getInstance(application);
        postInfoDao = db.postInfoDao();
        allPosts = postInfoDao.getAllPosts();
    }


    public LiveData<List<ResultModel>> getAllPosts() {
        return allPosts;
    }
    public void insertPosts (List<ResultModel> resultModel) {
        new InsertAsyncTask(postInfoDao).execute(resultModel);
    }

    class InsertAsyncTask extends AsyncTask<List<ResultModel>,Void,Void>{
        private PostInfoDao postInfoDao;

        public InsertAsyncTask(PostInfoDao postInfoDao) {
            this.postInfoDao = postInfoDao;
        }


        @Override
        protected Void doInBackground(List<ResultModel>... lists) {
            postInfoDao.insertPosts(lists[0]);
            return null;
        }
    }
}
