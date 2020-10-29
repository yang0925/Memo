package com.example.memo.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.R
import com.example.memo.adapter.MainRecyclerViewAdapter
import com.example.memo.data.MainRecyclerViewData
import com.example.memo.room.Memo
import com.example.memo.room.MemoDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show.*

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        title_textView.text = intent.getStringExtra("title")
        contents_textView.text = intent.getStringExtra("contents")

    }
}