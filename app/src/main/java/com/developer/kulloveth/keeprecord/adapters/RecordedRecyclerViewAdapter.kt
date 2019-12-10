package com.developer.kulloveth.keeprecord.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developer.kulloveth.keeprecord.R
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import com.developer.kulloveth.keeprecord.utils.RecyclerViewItemCLickListener
import kotlinx.android.synthetic.main.item_rv_record.view.*

class RecordedRecyclerViewAdapter(private val recyclerViewItemClickListener: RecyclerViewItemCLickListener) :
    ListAdapter<RecordedItemModel, RecordedRecyclerViewAdapter.RecordedViewHolder>(DIFF_UTIL) {
    //private var mRecordedItemList = mutableListOf<RecordedItemModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordedViewHolder {
        return RecordedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rv_record,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecordedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class RecordedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recordedItem: RecordedItemModel) {
            itemView.recorded_item_textview.text = recordedItem.recordTopic
            itemView.recorded_detail_textview.text = recordedItem.recordDetail
            itemView.recorded_date_textview.text = recordedItem.recordedDate
            itemView.setOnClickListener {
                recyclerViewItemClickListener.onItemCicked(recordedItem)

            }

        }
    }

    object DIFF_UTIL : DiffUtil.ItemCallback<RecordedItemModel>() {
        override fun areItemsTheSame(
            oldItem: RecordedItemModel,
            newItem: RecordedItemModel
        ): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecordedItemModel,
            newItem: RecordedItemModel
        ): Boolean {
            return newItem == oldItem
        }

    }

}