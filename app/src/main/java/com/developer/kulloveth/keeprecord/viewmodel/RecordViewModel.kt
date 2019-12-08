package com.developer.kulloveth.keeprecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.developer.kulloveth.keeprecord.database.RecordRepository
import com.developer.kulloveth.keeprecord.database.RecordRoomDatabase
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecordRepository

    val allRecords: LiveData<List<RecordedItemModel>>

    init {

        val recordsDao = RecordRoomDatabase.getDatabase(
            application
        ).recordDao()
        repository =
            RecordRepository(
                recordsDao
            )
        allRecords = repository.allRecords
    }

    fun insert(record: RecordedItemModel) = viewModelScope.launch {
        repository.insert(record)
    }
}