package com.ramadan.coffeeshifts.data

data class Shift(
    val name: String?,
    val role: String?,
    val start_date: String?,
    val color: String?,
    val end_date: String?,
    var start_time: String? = ""
)