package ru.e2e4.shopmobile.utils

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager


/**
 * Управление клавиатурой ввода
 */

/**
 * Показать клавиатуру
 * @param context контекст окна
 * @param view фокусируемый элемент инферсейса
 */
fun showKeyboard(context: Context, view: View) {
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
fun hideKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun areConfirmButtonIsPressed(actionId: Int, event: KeyEvent?): Boolean {
    return (actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_DONE
            || event == null
            || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
}