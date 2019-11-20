package ru.e2e4.shopmobile.utils.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * "Классическое" построение адаптера RecyclerView для отображения списка
 */
abstract class RecyclerViewAdapter(private val layoutId: Int) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}