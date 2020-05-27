package com.example.postsmvvmretrofit.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.postsmvvmretrofit.model.ResultModel;

@Database(entities = {ResultModel.class},version = 1,exportSchema = false)
public abstract class PostsInfoRoomDatabase extends RoomDatabase {

    public abstract PostInfoDao postInfoDao();
    public static final String DATABASE_NAME = "postinfo_database";

    private static PostsInfoRoomDatabase instance;

    public static synchronized PostsInfoRoomDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostsInfoRoomDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return instance;
    }

    private static Callback sRoomDatabaseCallback  = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private PostInfoDao postInfoDao;

        public PopulateDbAsyncTask(PostsInfoRoomDatabase db) {
            postInfoDao = db.postInfoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            postInfoDao.deleteAll();
            return null;
        }
    }
}
