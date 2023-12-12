package com.okproject.moviecollection.ui.screens.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.getYear(): Int {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(this, formatter)
        date.year
    } catch (_: Exception) {
        0
    }

}