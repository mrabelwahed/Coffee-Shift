package com.ramadan.coffeeshifts.navigation


interface Navigator {
    fun navigateTo(screen : Screen)
}

enum class Screen{
    COFFEE_SHIFTS_SCREEN,
    COFFEE_SHIFT_DETAIL_SCREEN
}