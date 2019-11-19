package ru.e2e4.shopmobile.utils.recycler

/**
 * Контракт для данных адаптера, работающего с [DiffUtilCallback]
 */
interface DiffUtilItem<V> {
    /**
     * Правило, по которому определяется уникальность элемента в списке
     * @param newProduct новый элемент списка
     */
    fun areItemsTheSame(newProduct: V): Boolean

    /**
     * Правило, по которому определяются сменяемые данные одинаковых элементов
     * чтобы определить, изменилось ли содержимое элемента
     * @param newProduct новый элемент списка
     */
    fun areContentsTheSame(newProduct: V): Boolean = false
}