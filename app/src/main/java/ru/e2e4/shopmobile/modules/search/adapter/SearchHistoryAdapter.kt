package ru.e2e4.shopmobile.modules.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_history_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.room.search.SearchHistory

class SearchHistoryAdapter(
    val data: List<SearchHistory>
) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.search_history_item, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SearchHistory) {
            itemView.apply {
                vHistoryText.text = item.text
            }
        }
    }
}