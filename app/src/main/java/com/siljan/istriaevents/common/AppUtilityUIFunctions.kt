package com.siljan.istriaevents.common

import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Locale

fun DialogFragment.createCustomDateRangePicker(title: String = "", validatorConstraints: CalendarConstraints = CalendarConstraints.Builder().build()) =
    MaterialDatePicker.Builder.dateRangePicker().apply {
        setTitleText(title)
        setCalendarConstraints(validatorConstraints)
    }.build()


fun Long.formatDate(customFormatter: String = DateFormat.DEFAULT.value): String {
    val dateFormat = SimpleDateFormat(customFormatter, Locale.getDefault())
    return dateFormat.format(this)
}

enum class DateFormat(val value: String) {
    DEFAULT("MMM dd")
}