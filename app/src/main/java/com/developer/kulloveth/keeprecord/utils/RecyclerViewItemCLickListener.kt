package com.developer.kulloveth.keeprecord.utils

import com.developer.kulloveth.keeprecord.model.RecordedItemModel

interface RecyclerViewItemCLickListener {
    fun onItemCicked(recordedItem: RecordedItemModel)
}