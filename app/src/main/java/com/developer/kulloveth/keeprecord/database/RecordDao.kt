package com.developer.kulloveth.keeprecord.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developer.kulloveth.keeprecord.model.RecordedItemModel


@Dao
interface RecordDao {

    @Query("Select * from RecordedItemModel ORDER by id ASC ")
    fun fetchAllRecord() : LiveData<List<RecordedItemModel>>

    @Query("Select * from RecordedItemModel where id = :id")
    suspend fun fetchRecordById(id : Int) : RecordedItemModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordedItemModel)
}