package com.siljan.istriaevents.common

import android.util.Patterns

fun validateEmailInput(input: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(input).matches()