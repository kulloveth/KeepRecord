package com.developer.kulloveth.keeprecord.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulloveth.keeprecord.R
import com.developer.kulloveth.keeprecord.adapters.RecordedRecyclerViewAdapter
import com.developer.kulloveth.keeprecord.model.RecordedItemModel
import com.developer.kulloveth.keeprecord.utils.RecyclerViewItemCLickListener
import com.developer.kulloveth.keeprecord.viewmodel.RecordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var recordedItemAdpter: RecordedRecyclerViewAdapter
    private lateinit var recordViewModel: RecordViewModel

    //  private lateinit var recordedItemList: List<RecordedItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recordedItemAdpter =
            RecordedRecyclerViewAdapter(
                object :
                    RecyclerViewItemCLickListener {
                    override fun onItemCicked(recordedItem: RecordedItemModel) {
                        starRecordedItemActivity(recordedItem.id)
                    }
                })


        rv_records.layoutManager = LinearLayoutManager(this)
        rv_records.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        recordViewModel.allRecords.observe(this, Observer { record ->
            record?.let {

                recordedItemAdpter.submitList(it.toMutableList())
                rv_records.adapter = recordedItemAdpter}
        })

        add.setOnClickListener {
            starRecordedItemActivity()
        }

    }

    private fun starRecordedItemActivity(id: Int? = null) {
        val intent = Intent(this, AddEditActivity::class.java)
        if(id != null){
            intent.putExtra(INTENT_EXTRA_KEY,id)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> {

            }

            R.id.sort_by_date_added -> {

            }
        }
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }


    override fun onClose(): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


    companion object {
        val INTENT_EXTRA_KEY = "BORROW_ID"
    }
}
