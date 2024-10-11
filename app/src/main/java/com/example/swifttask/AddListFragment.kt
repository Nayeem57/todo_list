package com.example.swifttask


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.swifttask.databinding.FragmentAddListBinding

class AddListFragment : Fragment() {
    lateinit var binding: FragmentAddListBinding
    var showTime: String? = null
    var showDate: String? = null


    lateinit var database : NoteDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddListBinding.inflate(inflater, container, false)

        database = Room.databaseBuilder(requireActivity(), NoteDataBase ::class.java, "Task-DB").allowMainThreadQueries().build()


        binding.buttonPickDate.setOnClickListener {
            pickADate()
        }

        binding.buttonPickTime.setOnClickListener {

            pickATime()

        }

        binding.buttonSubmitTask.setOnClickListener {

            val taskTitle = binding.TaskTitle.text.toString()
            val taskDetails = binding.TaskDetails.text.toString()
            val timeR = showTime ?: "00:00"
            val dateR = showDate ?: "00/00/0000"

            val note = Note(title = taskTitle, details = taskDetails, time = timeR, date = dateR)

            database.getNoteDao().insertData(note)

        }

        return binding.root
    }

    private fun pickATime() {

        val calendar = Calendar.getInstance()

        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val timePicker = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener()
            { _, selectedHour, selectedMinute ->

                val amPm = if (selectedHour < 12) "AM" else "PM"

                val formattedHour = if (selectedHour % 12 == 0) 12 else selectedHour % 12

                val formattedMinute = String.format("%02d", selectedMinute)

                showTime = "$formattedHour:$formattedMinute $amPm"

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
            requireActivity(), DatePickerDialog.OnDateSetListener()
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                showDate = "$selectedDayOfMonth, ${selectedMonth + 1}, $selectedYear"
                binding.buttonPickDate.text = showDate
            },
            year,
            month,
            day
        )
        datePicker.show()
    }
}