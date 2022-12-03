package com.ramadan.coffeeshifts.navigation

import androidx.fragment.app.FragmentActivity
import com.ramadan.coffeeshifts.R
import com.ramadan.coffeeshifts.ui.shifts.details.ShiftDetailsFragment
import com.ramadan.coffeeshifts.ui.shifts.list.ShiftListFragment
import javax.inject.Inject

class AppNavigator @Inject constructor(private val activity: FragmentActivity): Navigator {

    override fun navigateTo(screen: Screen) {
        val fragment = when(screen){
            Screen.COFFEE_SHIFTS_SCREEN -> ShiftListFragment()
            Screen.COFFEE_SHIFT_DETAIL_SCREEN -> ShiftDetailsFragment()
        }
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container , fragment)
            .addToBackStack(fragment.javaClass.canonicalName)
            .commit()
    }
}