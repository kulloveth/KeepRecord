package com.developer.kulloveth.keeprecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developer.kulloveth.keeprecord.database.RecordDao
import com.developer.kulloveth.keeprecord.database.RecordRoomDatabase
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditViewModel(application: Application) : AndroidViewModel(application) {


    val editRecordLiveData = MutableLiveData<RecordedItemModel>()

    private val recordDao: RecordDao = RecordRoomDatabase.getDatabase(
        application
    ).recordDao()

    fun insert(record: RecordedItemModel) = viewModelScope.launch {
        recordDao.insertRecord(record)
    }


    fun edit(id: Int) = viewModelScope.launch(Dispatchers.IO) {

        editRecordLiveData.postValue(recordDao.fetchRecordById(id))
    }


    fun update(record: RecordedItemModel) = viewModelScope.launch {
        recordDao.upRecordById(record)
    }
}