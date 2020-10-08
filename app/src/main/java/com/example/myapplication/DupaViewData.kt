package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import java.text.NumberFormat
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class DupaViewData {
    private val format = NumberFormat.getInstance().apply {
        minimumIntegerDigits = 2
    }

    val duration = MutableLiveData(DurationWrapper(Duration.ZERO))
    val durationRaw = MutableLiveData("")
    val durationFormatted = durationRaw.map {
        val number = it.toIntOrNull() ?: 0
        "${format.format(number / 100)}:${format.format(number % 100)}"
    }
}
