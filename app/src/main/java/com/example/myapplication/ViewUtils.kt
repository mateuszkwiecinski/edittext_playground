package com.example.myapplication

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService

fun View.requestFocusWithKeyboard() {
    requestFocus()
    context.getSystemService<InputMethodManager>()
        ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun selectOnFocus(source: View, target: View) {

}
