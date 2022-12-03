package com.ramadan.coffeeshifts.ui.shifts.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.coffeeshifts.R
import com.ramadan.coffeeshifts.common.AppConst
import com.ramadan.coffeeshifts.data.Shift
import com.ramadan.coffeeshifts.databinding.ItemShiftLayoutBinding
import javax.inject.Inject

class ShiftsAdapter (val data : List<Shift>): ListAdapter<Shift, ShiftsAdapter.ShiftsViewHolder>(
    DiffCallback()
){

    class ShiftsViewHolder(private val binding: ItemShiftLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(shift: Shift) {
            val context = binding.root.context
            binding.viewCellColor.setBackgroundColor(getColorByName(shift.color ?: AppConst.ColorNames.BLUE ))
            binding.tvTitle.text = context.getString(R.string.composite_title, shift.name , shift.role , shift.start_date)
            binding.tvTime.text = shift.start_time
        }

        private fun getColorByName(colorName : String) : Int {
           return when (colorName) {
               AppConst.ColorNames.RED -> Color.RED
               AppConst.ColorNames.GREEN -> Color.GREEN
               else -> Color.BLUE
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftsViewHolder {
        val binding =  ItemShiftLayoutBinding.inflate(LayoutInflater.from(parent.context) ,parent, false)
        return ShiftsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShiftsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class DiffCallback : DiffUtil.ItemCallback<Shift>() {
        override fun areItemsTheSame(oldItem: Shift, newItem: Shift): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Shift, newItem: Shift): Boolean {
            return  oldItem.name + oldItem.role == newItem.name + newItem.role
        }

    }
}