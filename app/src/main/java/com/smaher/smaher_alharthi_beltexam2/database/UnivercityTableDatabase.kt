package com.smaher.smaher_alharthi_beltexam2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UnivercityTable::class],version = 1,exportSchema = false)
abstract class UnivercityTableDatabase: RoomDatabase() {

    companion object{
        var instance: UnivercityTableDatabase?=null;
        fun getInstance(ctx: Context): UnivercityTableDatabase
        {
            if(instance !=null)
            {
                return  instance as UnivercityTableDatabase;
            }
            instance = Room.databaseBuilder(ctx, UnivercityTableDatabase::class.java,"playgroundDB").run { allowMainThreadQueries() }.build()
            return instance as UnivercityTableDatabase;
        }
    }
    abstract fun UniDao(): UnivercityTableDao;
}