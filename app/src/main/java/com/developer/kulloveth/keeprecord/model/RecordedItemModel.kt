package com.developer.kulloveth.keeprecord.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordedItemModel (

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null, val recordTopic: String, val recordDetail: String, val recordedDate: String
)