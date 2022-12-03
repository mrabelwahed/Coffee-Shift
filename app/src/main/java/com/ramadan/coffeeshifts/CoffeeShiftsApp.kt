package com.ramadan.coffeeshifts

import android.app.Application
import com.ramadan.coffeeshifts.data.Shift
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoffeeShiftsApp : Application(){
     val inMemoryShifts  = mutableListOf<Shift>()
}