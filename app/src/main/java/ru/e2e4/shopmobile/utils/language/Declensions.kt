package ru.e2e4.shopmobile.utils.language

import kotlin.math.abs

/**
 * Определяет форму слова в зависимомти от количества
 * TODO написать тесты
 *
 * @param count число, от которого будет зависеть форма слова
 * @param form1 первая форма слова, например Товар
 * @param form2 вторая форма слова - Товара
 * @param form3 третья форма множественного числа слова - Товаров
 */
fun numbericalFormOfNoun(count: Int, form1: String, form2: String, form3: String): String {
    val num = abs(count) % 100 // оставляем последние 2 числа
    val numX = num % 10 // оставляем последнее число
    return when {
        num in 11..19 -> form3
        numX in 2..4 -> form2
        numX == 1 -> form1
        else -> form3
    }
}

/**
 * Определяет множественность числа по количеству
 *
 * @param count количество
 * @param singular слово в единственном числе
 * @param plural слово во множественном числе числе
 */
fun pluralDefinition(count: Int, singular: String, plural: String): String {
    return if (count == 1) singular else plural
}