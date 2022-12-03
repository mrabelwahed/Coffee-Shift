package com.ramadan.coffeeshifts.domain.contract

import com.ramadan.coffeeshifts.data.Shift

interface ShiftsRepository {
    fun getShifts() : List<Shift>
    fun addShift(shift: Shift)
}