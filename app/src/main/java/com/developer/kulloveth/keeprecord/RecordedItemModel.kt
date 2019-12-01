package com.developer.kulloveth.keeprecord

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordedItemModel (

    @PrimaryKey(autoGenerate = true)
    var id:Int, val recordTopic: String, val recordDetail: String, val recordedDate: String


    )