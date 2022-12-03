package com.ramadan.coffeeshifts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.domain.usecase.AddShift
import com.ramadan.coffeeshifts.domain.usecase.GetShiftsUsecase
import com.ramadan.coffeeshifts.extensions.formatDate
import com.ramadan.coffeeshifts.extensions.getStartTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ShiftsViewModel @Inject constructor(private val getShift: GetShiftsUsecase , private val addShift :AddShift): ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Initial)
    val uiState : StateFlow<State> get() = _uiState
    var selectedColor : String = ""
    var selectedEmployee :String = ""
    var selectedRole: String = ""
    var isStartDateBtnClicked = false
    var isEndDateBtnClicked = false
    var day = 0
    var month = 0
    var year  = 0
    var hour = 0
    var min = 0
    var sec = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear  = 0
    var savedHour = 0
    var savedMin = 0

     fun getShifts () {
        _uiState.value = State.Loading
         viewModelScope.launch (Dispatchers.IO){
            try {
                val presentedData = getShift.invoke()
                    .map {
                    it.copy(start_date = it.start_date?.formatDate() , start_time = it.start_date?.getStartTime())
                }
                _uiState.value = State.Success (presentedData)
            }catch (e: Exception) {
                _uiState.value = State.Error(e.message)
            }
       }
    }

     fun addShift(shift: Shift){
          addShift.invoke(shift)
    }

    fun isValidShift() : Boolean {
        return selectedEmployee.isNotEmpty() &&
                selectedColor.isNotEmpty() &&
                selectedRole.isNotEmpty()
    }

    fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        min = cal.get(Calendar.MINUTE)
        sec = cal.get(Calendar.SECOND)
    }
}


sealed class State {
    object  Initial : State()
    object  Loading : State()
    data class  Success(val date : List<Shift>) : State()
    data class  Error( val errorMessage: String?) : State()
}