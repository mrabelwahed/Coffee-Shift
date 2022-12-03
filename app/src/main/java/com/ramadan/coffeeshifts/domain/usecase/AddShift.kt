package com.ramadan.coffeeshifts.domain.usecase

import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.domain.contract.ShiftsRepository
import javax.inject.Inject

class AddShift @Inject constructor(private val repository: ShiftsRepository){
    operator fun invoke (shift : Shift){
        repository.addShift(shift)
    }
}