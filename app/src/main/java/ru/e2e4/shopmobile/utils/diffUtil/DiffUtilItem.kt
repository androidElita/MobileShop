package ru.e2e4.shopmobile.utils.diffUtil

interface DiffUtilItem<V> {

    fun areItemsTheSame(newProduct: V): Boolean
    fun areContentsTheSame(newProduct: V): Boolean
}