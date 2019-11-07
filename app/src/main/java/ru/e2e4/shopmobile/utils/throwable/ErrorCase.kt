package ru.e2e4.shopmobile.utils.throwable

/**
 * Виды возможных ошибок при выполнении запроса к серверу:
 *
 * [INTERNAL_NETWORK_ERROR] - внутреняя ошибка сети (code 500)
 * [BAD_REQUEST] - передача неккоректных данных (code 400)
 * [CONNECT_ERROR] - отсутствие соединения с интернетом
 * [TIMEOUT] - тайи-аут соединения
 * [UNKNOWN_ERROR] - неизвестная ошибка
 */
enum class ErrorCase {
    INTERNAL_NETWORK_ERROR,
    BAD_REQUEST,
    TIMEOUT,
    CONNECT_ERROR,
    UNKNOWN_ERROR
}