package com.ramadan.coffeeshifts.ui.shifts.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.databinding.FragmentShiftListBinding
import com.ramadan.coffeeshifts.extensions.hideLoading
import com.ramadan.coffeeshifts.extensions.showLoading
import com.ramadan.coffeeshifts.extensions.showSnackMessage
import com.ramadan.coffeeshifts.navigation.AppNavigator
import com.ramadan.coffeeshifts.navigation.Screen
import com.ramadan.coffeeshifts.ui.ShiftsViewModel
import com.ramadan.coffeeshifts.ui.State
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShiftListFragment: Fragment() {
    private lateinit var binding : FragmentShiftListBinding
    private val viewModel: ShiftsViewModel by viewModels()
     lateinit var shiftsAdapter: ShiftsAdapter
    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShiftListBinding.inflate(inflater,container,false)
        attachObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShifts()
        handleNavigateToDetailsScreen()
    }

    private  fun attachObserver(){
        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect {
                when(it){
                    is State.Loading -> {
                        showLoading()
                    }
                    is State.Error -> {
                        hideLoading()
                        it.errorMessage?.let {msg -> showSnackMessage(msg)}
                    }
                    is State.Success -> {
                        hideLoading()
                        setData(it.date)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setData(data: List<Shift>){
       shiftsAdapter = ShiftsAdapter(data)
        binding.rvShiftList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shiftsAdapter
        }
        shiftsAdapter.submitList(data)
    }

    private fun handleNavigateToDetailsScreen(){
        binding.btnAddShifts.setOnClickListener{
            appNavigator.navigateTo(Screen.COFFEE_SHIFT_DETAIL_SCREEN)
        }
    }
}