package ru.e2e4.shopmobile.modules.search.adapter

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.search_history_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.room.search.SearchHistory
import ru.e2e4.shopmobile.utils.recycler.RecyclerAdapterAbstract
import ru.e2e4.shopmobile.utils.recycler.RecyclerDiffUtilAdapterAbstract

class SearchHistoryAdapter : RecyclerDiffUtilAdapterAbstract<SearchHistory, SearchHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getInflater(parent, R.layout.search_history_item))
    }

    inner class ViewHolder(itemView: View) : RecyclerAdapterAbstract<SearchHistory, ViewHolder>.ViewHolder(itemView) {
        override fun bind(item: SearchHistory) {
            itemView.apply {
                vHistoryText.text = item.text
            }
        }
    }
}