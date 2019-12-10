package com.developer.kulloveth.keeprecord.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.developer.kulloveth.keeprecord.R
import com.developer.kulloveth.keeprecord.database.RecordRepository
import com.developer.kulloveth.keeprecord.database.RecordRoomDatabase
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import com.developer.kulloveth.keeprecord.utils.DateHelper
import com.developer.kulloveth.keeprecord.viewmodel.AddEditViewModel
import com.developer.kulloveth.keeprecord.viewmodel.RecordViewModel
import kotlinx.android.synthetic.main.activity_add_edit.*
import java.util.*


class AddEditActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {


    private var recordedId: Int? = null
    private val calendar = Calendar.getInstance()
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var recordViewModel: AddEditViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        recordViewModel = ViewModelProvider(this).get(AddEditViewModel::class.java)
         var recordedId = intent.extras?.getInt(MainActivity.INTENT_EXTRA_KEY)

        if (recordedId != null) {
           recordViewModel.edit(recordedId)
            recordViewModel.editRecordLiveData.observe(this,
                Observer {
                    title_of_record.setText(it.recordTopic)
                    detail_of_record.setText(it.recordDetail)
                    recorded_date_tv.text = it.recordedDate

                })
//                it[recordedId]
//            })


        }
        datePickerDialog = DatePickerDialog(
            this, this, calendar.get(Calendar.YEAR), calendar.get(
                Calendar.MONTH
            ), calendar.get(Calendar.DAY_OF_MONTH)
        )

        date_recorded.setOnClickListener {
            datePickerDialog.show()
        }

        fab.setOnClickListener {
          insertAndUpdateRecord(recordedId)
            finish()
        }
    }

    fun insertAndUpdateRecord(recordedId : Int?){


        val mrecordItem =
            RecordedItemModel(
                id = recordedId,
                recordTopic = title_of_record.text.toString(),
                recordDetail = detail_of_record.text.toString(),
                recordedDate = recorded_date_tv.text.toString()
            )
        if (recordedId == null) {
            recordViewModel.insert(mrecordItem)
        }else {
            recordViewModel.update(
                mrecordItem
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_edit_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.delete -> {
                deleteRecordedItem()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteRecordedItem() {

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        recorded_date_tv.text = DateHelper.formatDate(calendar)
    }
}
