package com.developer.kulloveth.keeprecord.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.developer.kulloveth.keeprecord.model.RecordedItemModel


@Dao
interface RecordDao {

    @Query("Select * from RecordedItemModel ORDER by id ASC ")
    fun fetchAllRecord(): LiveData<List<RecordedItemModel>>

    @Query("Select * from RecordedItemModel where id = :id")
    suspend fun fetchRecordById(id: Int): RecordedItemModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordedItemModel)

//    @Query("Update RecordedItemModel SET recordTopic = :recordedTopic,recordDetail = :recordedDetail , recordedDate = :recordedDate where id = :id")
//    suspend fun updateRecordById(recordedTopic:String,recordedDetail:String,recordedDate:String,id:Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upRecordById(record: RecordedItemModel)
}