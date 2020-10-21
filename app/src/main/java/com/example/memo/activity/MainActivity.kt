package com.example.memo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.*
import com.example.memo.adapter.MainRecyclerViewAdapter
import com.example.memo.data.MainRecyclerViewData
import com.example.memo.room.Memo
import com.example.memo.room.MemoDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainRecyclerViewAdapter(arrayListOf())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val dao = MemoDatabase.getInstance(context = applicationContext).memoDao()

        Thread{
            dao.getAll().forEach {
                adapter.addItem(MainRecyclerViewData(it.title, it.subtitle))
            }
        }.start()

        button.setOnClickListener {
            adapter.addItem(MainRecyclerViewData("hi", "bye"))

            Thread{
                dao.addMemo(memo = Memo(0, "hi","bye"))
            }.start()
        }
    }
}