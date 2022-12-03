package com.ramadan.coffeeshifts.ui.shifts.details

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ramadan.coffeeshifts.R
import com.ramadan.coffeeshifts.common.AppConst.ShiftDetailData.colorsArray
import com.ramadan.coffeeshifts.common.AppConst.ShiftDetailData.employeeArray
import com.ramadan.coffeeshifts.common.AppConst.ShiftDetailData.roleArray
import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.databinding.FragmentShiftDetailBinding
import com.ramadan.coffeeshifts.extensions.getStartTime
import com.ramadan.coffeeshifts.extensions.showSnackMessage
import com.ramadan.coffeeshifts.ui.ShiftsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShiftDetailsFragment: Fragment() , DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener{
    private lateinit var binding : FragmentShiftDetailBinding
    private val viewModel: ShiftsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShiftDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleBackButton()
        setupEmployeeDropDownMenu()
        setupRoleDropDownMenu()
        setupColorDropDownMenu()
        handleSaveButton()
        handleAddStartDate()
        handleAddEndDate()
    }

    private fun handleAddEndDate() {
        binding.btnAddEndDate.setOnClickListener{
            viewModel.isStartDateBtnClicked = false
            viewModel.isEndDateBtnClicked = true
            viewModel.getDateTimeCalendar()
            DatePickerDialog(requireContext(),this,viewModel.year , viewModel.month , viewModel.day).show()
        }
    }

    private fun handleAddStartDate() {
        binding.btnAddStartDate.setOnClickListener{
            viewModel.isStartDateBtnClicked = true
            viewModel.isEndDateBtnClicked = false
            viewModel.getDateTimeCalendar()
            DatePickerDialog(requireContext(),this,viewModel.year , viewModel.month , viewModel.day).show()
        }
    }

    private fun handleBackButton(){
        binding.btnBack.setOnClickListener {
           closeScreen()
        }
    }

    private fun handleSaveButton(){
        binding.btnSave.setOnClickListener{
           if (viewModel.isValidShift()){
               val newShift = Shift(
                   name = viewModel.selectedEmployee,
                   role = viewModel.selectedRole,
                   color = viewModel.selectedColor,
                   start_date = binding.edtStartDate.text.toString(),
                   end_date = binding.edtEndDate.text.toString()
               )
                viewModel.addShift(newShift)
               closeScreen()
           }else{
               showSnackMessage(getString(R.string.not_valid_shift))
           }
        }
    }
    private fun setupEmployeeDropDownMenu() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.item_dropdown, employeeArray)
        binding.acEmployee.setAdapter(adapter)
        binding.acEmployee.setOnItemClickListener { _, _, position, _ ->
            val value = adapter.getItem(position) ?: ""
            viewModel.selectedEmployee = value
        }
    }

    private fun setupRoleDropDownMenu() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.item_dropdown, roleArray)
        binding.acRole.setAdapter(adapter)
        binding.acRole.setOnItemClickListener { _, _, position, _ ->
            val value = adapter.getItem(position) ?: ""
            viewModel.selectedRole = value
        }
    }

    private fun setupColorDropDownMenu() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.item_dropdown, colorsArray)
        binding.acColor.setAdapter(adapter)
        binding.acColor.setOnItemClickListener { _, _, position, _ ->
            val value = adapter.getItem(position) ?: ""
            viewModel.selectedColor = value
        }
    }

    private fun closeScreen(){
        activity?.onBackPressed()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.savedDay = dayOfMonth
        viewModel.savedMonth = month
        viewModel.savedYear = year
        viewModel.getDateTimeCalendar()
        TimePickerDialog(requireContext(), this, viewModel.hour,viewModel.min,false).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, min: Int) {
        viewModel.savedHour = hourOfDay
        viewModel.savedMin  = min
        val text = "${viewModel.savedYear}-${viewModel.savedMonth +1 }-${viewModel.savedDay} ${viewModel.savedHour}:00:00 -${viewModel.savedMin}:00"
        if (viewModel.isStartDateBtnClicked)
             binding.edtStartDate.setText(text)
        else if (viewModel.isEndDateBtnClicked)
            binding.edtEndDate.setText(text)
    }
}