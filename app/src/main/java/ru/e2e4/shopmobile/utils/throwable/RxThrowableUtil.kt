package ru.e2e4.shopmobile.utils.throwable

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Синглтон для обработки [Throwable] при использовании rx
 */
class RxThrowableUtil {

    /**
     * Инспектирует [t] и определяет тип ошибки
     * @param t ошибка запроса
     * @return тип ошибки, один из [ErrorCase]
     */
    fun inspect(t: Throwable): ErrorCase = when (t) {
        is HttpException -> when (t.code()) {
            HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorCase.INTERNAL_NETWORK_ERROR
            HttpURLConnection.HTTP_BAD_REQUEST -> ErrorCase.BAD_REQUEST
            else -> ErrorCase.UNKNOWN_ERROR
        }
        is ConnectException, is UnknownHostException -> ErrorCase.CONNECT_ERROR
        is TimeoutException -> ErrorCase.TIMEOUT
        else -> ErrorCase.UNKNOWN_ERROR
    }
}