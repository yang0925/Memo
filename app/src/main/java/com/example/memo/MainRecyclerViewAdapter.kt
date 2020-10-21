package com.example.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_recycler_item.view.*

class MainRecyclerViewAdapter(val list: ArrayList<MainRecyclerViewData>): RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    fun addItem(item: MainRecyclerViewData) {
        list.add(item)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return MainRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(list[position].title, list[position].subtitle)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainRecyclerViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(title: String, subtitle: String) {
            view.title.text = title
            view.subtitle.text = subtitle
        }
    }
}
