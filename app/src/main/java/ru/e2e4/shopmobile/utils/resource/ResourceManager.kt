package ru.e2e4.shopmobile.utils.resource

/**
 * Описывает необходимые строки из ресурсов Android, которые
 * используются в слоях не зависимых от платформы
 */
interface ResourceManager {
    val found: List<String>
    val goods: List<String>
    val searchHistoryEmpty: String
    val searchEmpty: String
}