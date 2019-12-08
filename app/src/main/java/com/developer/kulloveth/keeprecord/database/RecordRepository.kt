package com.developer.kulloveth.keeprecord.database

import androidx.lifecycle.LiveData
import com.developer.kulloveth.keeprecord.database.RecordDao
import com.developer.kulloveth.keeprecord.model.RecordedItemModel

class RecordRepository(private val record: RecordDao) {

    val allRecords: LiveData<List<RecordedItemModel>> = record.fechALlRecord()

    suspend fun insert(records: RecordedItemModel) {
        record.insertRecord(records)
    }
}