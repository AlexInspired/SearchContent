package com.example.searchcontent.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.searchcontent.data.database.dao.SearchEntityDao;
import com.example.searchcontent.data.entity.SearchEntity;

@Database(entities = {SearchEntity.class}, version = ApplicationDb.VERSION)
public abstract class ApplicationDb extends RoomDatabase {
    static final int VERSION = 1;
    public abstract SearchEntityDao getSearchEntityDao();
}