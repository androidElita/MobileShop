package ru.e2e4.shopmobile.modules.catalog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_recycler_category_item.view.*
import kotlinx.android.synthetic.main.catalog_recycler_category_item.view.vImageCategory
import kotlinx.android.synthetic.main.catalog_recycler_subcategory_item.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode

class CategoriesAdapter(
    private val categories: List<CategoriesNode>
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onItemClickListener: (subcategory: CategoriesNode) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            VIEW_TYPE_CATEGORIES ->
                layoutInflater.inflate(R.layout.catalog_recycler_category_item, parent, false)
            else ->
                layoutInflater.inflate(R.layout.catalog_recycler_subcategory_item, parent, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (categories[position].fullName.size == 1) VIEW_TYPE_CATEGORIES
        else VIEW_TYPE_SUBCATEGORIES
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.itemView.setOnClickListener { onItemClickListener(categories[position]) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryNode: CategoriesNode) {
            when (itemViewType) {
                //TODO изменить URL картинки, после того, как эта реализация будет на бэке
                VIEW_TYPE_CATEGORIES -> {
                    itemView.vCategory.text = categoryNode.name
                    Picasso.get()
                        .load("https://images.e2e4online.ru/o/5697910.jpg")
                        .placeholder(R.drawable.ic_icon_picture)
                        .fit()
                        .into(itemView.vImageCategory)
                }
                VIEW_TYPE_SUBCATEGORIES -> {
                    itemView.vSubcategory.text = categoryNode.name
                    Picasso.get()
                        .load("https://images.e2e4online.ru/o/5697910.jpg")
                        .placeholder(R.drawable.ic_icon_picture)
                        .fit()
                        .into(itemView.vImageCategory)
                }
            }
        }
    }

    companion object {
        const val VIEW_TYPE_CATEGORIES = 1
        const val VIEW_TYPE_SUBCATEGORIES = 2
    }
}