package com.smaher.smaher_alharthi_beltexam2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UnivercityTableDao {
    @Query("SELECT * FROM UnivercityTable")
    fun getunivercity(): LiveData<List<UnivercityTable>>


    @Insert
    fun insertunivercity(univercity: UnivercityTable)

    @Update
    fun updatenivercity(univercity: UnivercityTable)

    @Delete
    fun deleteunivercity(univercity: UnivercityTable)

}