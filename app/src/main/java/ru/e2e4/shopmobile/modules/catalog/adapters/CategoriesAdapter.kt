package ru.e2e4.shopmobile.modules.catalog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_recycler_category_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode

class CategoriesAdapter(
    private val categories: List<CategoriesNode>,
    @LayoutRes private val layoutItem: Int
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onItemClickListener: (subcategories: CategoriesNode) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutItem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.itemView.setOnClickListener { onItemClickListener(categories[position]) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryNode: CategoriesNode) {
            itemView.vCategory.text = categoryNode.name
            Picasso.get()
                .load("https://images.e2e4online.ru/o/861281.jpg")
                .placeholder(R.drawable.ic_icon_picture)
                .fit()
                .into(itemView.vImageCategory)
        }
    }
}