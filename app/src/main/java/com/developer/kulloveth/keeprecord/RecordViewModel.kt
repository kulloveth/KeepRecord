package com.developer.kulloveth.keeprecord

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecordRepository

    val allRecords: LiveData<List<RecordedItemModel>>

    init {

        val recordsDao = RecordRoomDatabase.getDatabase(application).recordDao()
        repository = RecordRepository(recordsDao)
        allRecords = repository.allRecords
    }

    fun insert(record: RecordedItemModel) = viewModelScope.launch {
        repository.insert(record)
    }
}