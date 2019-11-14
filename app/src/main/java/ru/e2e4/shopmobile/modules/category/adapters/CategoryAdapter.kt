package ru.e2e4.shopmobile.modules.category.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_recycler_category_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.category.data.CategoryNode

class CategoryAdapter(private val categories: List<CategoryNode>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_recycler_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryNode: CategoryNode) {
            itemView.vCategory.text = categoryNode.name

        }
    }
}