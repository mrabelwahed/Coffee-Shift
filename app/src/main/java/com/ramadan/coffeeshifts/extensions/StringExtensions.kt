package com.ramadan.coffeeshifts.extensions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.formatDate() : String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss -mm:ss")
    val convertedDate: Date?
    return try {
        convertedDate = dateFormat.parse(this)
        val newFormat = SimpleDateFormat("EEE MM/dd")
        val finalDateString = convertedDate?.let { newFormat.format(it) }
        finalDateString
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun String.getStartTime() : String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss -mm:ss")
    val convertedDate: Date?
    return try {
        convertedDate = dateFormat.parse(this)
        val newFormat = SimpleDateFormat("hh.mm aa")
        val finalDateString = convertedDate?.let { newFormat.format(it) }
        finalDateString
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}


