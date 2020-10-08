package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import java.text.NumberFormat

class DupaViewData {
    private val format = NumberFormat.getInstance().apply {
        minimumIntegerDigits = 2
    }

    val durationRaw = MutableLiveData("")
    val durationFormatted = durationRaw.map {
        val number = it.toIntOrNull() ?: 0
        "${format.format(number / 100)}:${format.format(number % 100)}"
    }
}
