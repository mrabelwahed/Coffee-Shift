package com.ramadan.coffeeshifts.data

import android.content.Context
import com.google.gson.Gson
import com.ramadan.coffeeshifts.CoffeeShiftsApp
import com.ramadan.coffeeshifts.domain.contract.ShiftsRepository
import javax.inject.Inject

private const val FILE_NAME = "shifts.json"

class ShiftsRepositoryImpl @Inject constructor(private val appContext: Context) : ShiftsRepository {
    private val application by lazy {
        (appContext as CoffeeShiftsApp)
    }

    override fun getShifts(): List<Shift> {
        if (application.inMemoryShifts.isEmpty()) {
            val jsonString = appContext.assets.open(FILE_NAME).bufferedReader().use {
                it.readText()
            }
            val data = Gson().fromJson(
                jsonString,
                Shifts::class.java
            )
            application.inMemoryShifts.addAll(data.shifts)
        }
        return application.inMemoryShifts
    }

    override fun addShift(shift: Shift) {
        application.inMemoryShifts.add(shift)
    }

}