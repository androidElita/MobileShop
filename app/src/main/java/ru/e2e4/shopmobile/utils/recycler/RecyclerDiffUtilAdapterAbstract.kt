package ru.e2e4.shopmobile.utils.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * Абстрактный адаптер, работающий с учетом [DiffUtilCallback]
 */
abstract class RecyclerDiffUtilAdapterAbstract<T: DiffUtilItem<T>, V : RecyclerAdapterAbstract<T, V>.ViewHolder> :
    RecyclerAdapterAbstract<T, V>()  {

    override var data: List<T> = listOf()
        set(value) {
            val diffUtil = DiffUtilCallback(field, value)
            val productDiffResult = DiffUtil.calculateDiff(diffUtil)
            productDiffResult.dispatchUpdatesTo(this)
            field = value
        }
}