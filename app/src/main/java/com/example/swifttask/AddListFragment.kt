package com.example.swifttask
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.swifttask.databinding.FragmentAddListBinding

class AddListFragment : Fragment() {
    lateinit var binding: FragmentAddListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddListBinding.inflate(inflater, container, false)

        binding.buttonPickDate.setOnClickListener {
            pickADate()
        }

        binding.buttonPickTime.setOnClickListener {

            pickATime()

        }

        return binding.root
    }

    private fun pickATime() {

        val calendar = Calendar.getInstance()

        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val timePicker = TimePickerDialog(
            requireActivity(),
            { _, selectedHour, selectedMinute ->

                val amPm = if (selectedHour < 12) "AM" else "PM"

                val formattedHour = if (selectedHour % 12 == 0) 12 else selectedHour % 12

                val formattedMinute = String.format("%02d", selectedMinute)

                val showTime = "$formattedHour:$formattedMinute $amPm"

                binding.buttonPickTime.text = showTime
            },
            hour,
            minute,
            false
        )
        timePicker.show()
    }


    private fun pickADate() {

        val calender = Calendar.getInstance()

        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val datePicker = DatePickerDialog(
            requireActivity(),DatePickerDialog.OnDateSetListener()
            { _,  selectedYear, selectedMonth, selectedDayOfMonth ->

                val showDate = "$selectedDayOfMonth, ${selectedMonth + 1}, $selectedYear"
                binding.buttonPickDate.text = showDate
            },
            year,
            month,
            day
        )
        datePicker.show()
    }
}