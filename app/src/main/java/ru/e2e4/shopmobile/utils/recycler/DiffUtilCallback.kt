package ru.e2e4.shopmobile.utils.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * Класс определения правил для динамического (анимированного) обновления списка RecyclerView
 * В класс передаются два списка, которые по-элементно сравниваются между собой
 * Работает быстрее, чем полное обновление списка через notifyDataSetChanged() в RecyclerView.Adapter
 *
 * @param oldList старый список
 * @param newList новый список, заменяющий [oldList]
 */
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