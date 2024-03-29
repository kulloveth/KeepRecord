package com.developer.kulloveth.keeprecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.developer.kulloveth.keeprecord.database.RecordDao
import com.developer.kulloveth.keeprecord.database.RecordRoomDatabase
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {


    private val recordDao: RecordDao = RecordRoomDatabase.getDatabase(
        application
    ).recordDao()

    val allRecords: LiveData<List<RecordedItemModel>> = recordDao.fetchAllRecord()





}