package com.developer.kulloveth.keeprecord

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Record {

    @Query("Select * from RecordedItemModel ORDER by id ASC ")
    fun fechALlRecord() : LiveData<List<RecordedItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordedItemModel)
}