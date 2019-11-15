package ru.e2e4.shopmobile.utils.diffUtil

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<V : DiffUtilItem<V>>(
    private val oldList: List<V>,
    private val newList: List<V>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.areItemsTheSame(newProduct)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.areContentsTheSame(newProduct)
    }
}