package com.example.memo.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.*
import com.example.memo.adapter.MainRecyclerViewAdapter
import com.example.memo.data.MainRecyclerViewData
import com.example.memo.room.Memo
import com.example.memo.room.MemoDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_write.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainRecyclerViewAdapter(arrayListOf())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val dao = MemoDatabase.getInstance(context = applicationContext).memoDao()

        Thread{
            dao.getAll().forEach {
                adapter.addItem(MainRecyclerViewData(it.title, it.subtitle))
            }
        }.start()

        var handler = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.getItem(viewHolder.adapterPosition)
                Thread{
                    item?.let{
                        dao.delete(it.title, it.subtitle)
                    }
                }.start()
                adapter.removeAt(viewHolder.adapterPosition)
            }
        })

        handler.attachToRecyclerView(recyclerView)
        write_button.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivityForResult(intent,100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                100 -> {
                    val dao = MemoDatabase.getInstance(context = applicationContext).memoDao()

                    adapter.addItem(MainRecyclerViewData(data!!.getStringExtra("title").toString(), data!!.getStringExtra("subtitle").toString()))

                    Thread{
                        dao.addMemo(memo = Memo(0, data!!.getStringExtra("title").toString(),data!!.getStringExtra("subtitle").toString()))
                    }.start()

                }
            }
        }
    }
}

