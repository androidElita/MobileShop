package ru.e2e4.shopmobile.modules.search.adapter

import kotlinx.android.synthetic.main.search_history_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.utils.recycler.RecyclerDiffUtilAdapter

class SearchHistoryAdapter : RecyclerDiffUtilAdapter<SearchItem>(R.layout.search_history_item) {

    var onClickListener: (SearchItem) -> Unit = { }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data[position]
        holder.itemView.apply {
            vHistoryText.text = value.text
            vHistoryIcon.setImageResource(value.icon)
            setOnClickListener { onClickListener(value) }
        }
    }
}