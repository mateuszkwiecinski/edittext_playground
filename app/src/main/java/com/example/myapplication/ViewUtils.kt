package com.example.myapplication

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.requestFocusWithKeyboard() {
    requestFocus()
    context.getSystemService<InputMethodManager>()
        ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
