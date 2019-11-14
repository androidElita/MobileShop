package ru.e2e4.shopmobile.components.customView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import ru.e2e4.shopmobile.utils.SoftKeyboardUtils

/**
 * Кастомное вью переопределяющее часто используемые события ввода при поиске
 */
class SearchInputText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    fun afterTextChanged(listener: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                listener(text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    fun areConfirmButtonIsPressed(listener: (String) -> Unit) {
        setOnEditorActionListener { v, actionId, event ->
            if (SoftKeyboardUtils.areConfirmButtonIsPressed(actionId, event)) {
                listener(v.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    fun clean() {
        setText("")
    }
}