package com.example.memo.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.MainRecyclerViewData
import com.example.memo.R
import com.example.memo.activity.ShowActivity
import kotlinx.android.synthetic.main.activity_write.*
import kotlinx.android.synthetic.main.main_recycler_item.view.*

class MainRecyclerViewAdapter(val list: ArrayList<MainRecyclerViewData>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    fun addItem(item: MainRecyclerViewData) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): MainRecyclerViewData? {
        return list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_item, parent, false)

        return MainRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(list[position].title, list[position].contents)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, ShowActivity::class.java)
            intent.putExtra("title", list[position].title)
            intent.putExtra("contents", list[position].contents)
            ContextCompat.startActivity(holder.itemView.context, intent,null)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MainRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(title: String, contents: String) {
            view.title.text = title
            view.contents.text = contents


        }
    }
}
