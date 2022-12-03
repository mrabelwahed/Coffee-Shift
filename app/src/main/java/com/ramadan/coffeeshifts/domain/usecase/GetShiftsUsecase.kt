package com.ramadan.coffeeshifts.domain.usecase
import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.domain.contract.ShiftsRepository
import com.ramadan.coffeeshifts.extensions.formatDate
import com.ramadan.coffeeshifts.extensions.getStartTime
import javax.inject.Inject

class GetShiftsUsecase @Inject constructor(private val repository: ShiftsRepository){
    operator fun invoke():List<Shift> {
        return repository.getShifts().sortedByDescending { it.start_date }
    }
}