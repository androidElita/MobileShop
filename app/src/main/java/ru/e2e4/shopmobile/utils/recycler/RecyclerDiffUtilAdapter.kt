package ru.e2e4.shopmobile.utils.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * Абстрактный адаптер, работающий с DiffUtil
 */
abstract class RecyclerDiffUtilAdapter<T: DiffUtilItem<T>>(layoutId: Int) :
    RecyclerViewAdapter(layoutId) {

    var data: List<T> = listOf()
        set(value) {
            val diffUtil = DiffUtilCallback(field, value)
            val productDiffResult = DiffUtil.calculateDiff(diffUtil)
            productDiffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemCount() = data.size
}