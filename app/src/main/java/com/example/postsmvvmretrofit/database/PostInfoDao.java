package com.example.postsmvvmretrofit.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.postsmvvmretrofit.model.ResultModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PostInfoDao {

    @Insert(onConflict = REPLACE)
    void insertAll(ResultModel resultModel);

    @Query("SELECT * FROM post_info ORDER BY id ASC")
    LiveData<List<ResultModel>> getAllPosts();

    @Query("DELETE FROM post_info")
    void deleteAll();

    @Insert(onConflict = REPLACE)
    void insertPosts(List<ResultModel> resultModel);
}
