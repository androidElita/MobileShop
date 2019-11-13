package ru.e2e4.shopmobile.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Управление клавиатурой ввода
 */
object SoftKeyboardUtils {

    /**
     * Показать клавиатуру
     * @param context контекст окна
     * @param view фокусируемый элемент инферсейса
     */
    fun show(context: Context, view: View) {
        if (view.requestFocus()) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    /**
     * Скрыть клавиатуру
     * @param context контекст окна
     * @param view фокусируемый элемент инферсейса
     */
    fun hide(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}