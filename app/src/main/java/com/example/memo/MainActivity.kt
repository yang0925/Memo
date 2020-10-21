package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainRecyclerViewAdapter(arrayListOf(MainRecyclerViewData("hi", "bye")))

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        button.setOnClickListener {
            adapter.addItem(MainRecyclerViewData("hi", "bye"))
        }
    }
}