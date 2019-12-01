package com.developer.kulloveth.keeprecord

import androidx.lifecycle.LiveData

class RecordRepository(private val record: Record) {

    val allRecords: LiveData<List<RecordedItemModel>> = record.fechALlRecord()

    suspend fun insert(records: RecordedItemModel) {
        record.insertRecord(records)
    }
}