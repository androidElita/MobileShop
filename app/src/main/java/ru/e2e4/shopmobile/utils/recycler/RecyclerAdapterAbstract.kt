package ru.e2e4.shopmobile.utils.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * "Классическое" построение адаптера RecyclerView для отображения списка
 */
abstract class RecyclerAdapterAbstract<T, V : RecyclerAdapterAbstract<T, V>.ViewHolder>
    : RecyclerView.Adapter<V>()  {

    open var data: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun getInflater(parent: ViewGroup, layoutId: Int): View = LayoutInflater.from(parent.context)
        .inflate(layoutId, parent, false)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: V, position: Int) {
        val value = data[position]
        holder.bind(value)
    }

    abstract inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}