package com.smaher.smaher_alharthi_beltexam2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UnivercityTable")
data class UnivercityTable(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "note") var note: String,
    )

