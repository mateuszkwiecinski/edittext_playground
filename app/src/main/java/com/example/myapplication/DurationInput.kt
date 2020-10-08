package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import java.text.NumberFormat
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.minutes

@SuppressLint("SetTextI18n")
@OptIn(ExperimentalTime::class)
class DurationInput(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {


    private val formatter = NumberFormat.getInstance().apply {
        minimumIntegerDigits = 2
        isGroupingUsed = false
    }
    var duration: Duration = Duration.ZERO
        set(value) {
            field = value
            changeListeners.forEach { it.invoke(value) }
        }
    private val changeListeners = mutableListOf<(Duration) -> Unit>()

    fun addChangeListener(listener: (Duration) -> Unit) {
        changeListeners.add(listener)
    }

    fun removeChangeListener(listener: (Duration) -> Unit) {
        changeListeners.remove(listener)
    }

    init {
        setText(duration.formatted)
        gravity = Gravity.CENTER
        addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                removeTextChangedListener(this)

                if (s != null) {
                    val raw = s.toString()
                    val number = raw.replace(":", "").toIntOrNull() ?: 0

                    duration = (number / 100).hours + (number % 100).minutes

                    s.clear()
                    s.insert(0, duration.formatted)
                    setSelection(s.length)
                }
                addTextChangedListener(this)
            }

        })
    }

    private val Duration.formatted: String
        get() {
            val (hours, minutes) = toComponents { hours, minutes, _, _ -> hours to minutes }
            return "${formatter.format(hours)}:${formatter.format(minutes)}"
        }
}

@BindingAdapter("duration")
fun DurationInput.getDuration(wrapped: DurationWrapper) {
    duration = wrapped.duration
}

@InverseBindingAdapter(attribute = "duration")
fun DurationInput.setDuration() = DurationWrapper(duration)

@OptIn(ExperimentalTime::class)
@BindingAdapter("durationAttrChanged")
fun DurationInput.setListeners(
    attrChange: InverseBindingListener
) {
    val newValue: (Duration?) -> Unit = { attrChange.onChange() }
    val oldValue = ListenerUtil.trackListener(this, newValue, R.id.durationListener);
    if (oldValue != null) {
        removeChangeListener(oldValue)
    }
    addChangeListener(newValue)
}
