package edu.tcu.dotnguyen.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button)
        // This method is to call when pick a date
        button.setOnClickListener { handleDatePicker() }

    }

    private fun handleDatePicker() {
//        Toast.makeText(this,"Button is clicked", Toast.LENGTH_SHORT).show()
//        println("Button is clicked")

        val selectedDate = findViewById<TextView>(R.id.selected_date_tv)
        val minutes = findViewById<TextView>(R.id.minutes_tv)

        // Show date picker calendar
        val calendar = Calendar.getInstance()

        // TimeStamp 1: Today timestamp
        // get today
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

//        Toast.makeText(this,"Day: $currentMonth/$currentDay/$currentYear", Toast.LENGTH_SHORT).show()

        // Convert it to minutes
        val currentDateInMillis = calendar.timeInMillis
        val currentDateInMin = currentDateInMillis / 60000

        // TimeStamp 2: Picked timestamp
        // Pick one contain listener (this is OK listener)
        //  When OK button is clicked
        val dialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            val pickedDateInMillis = calendar.timeInMillis
            val pickedDateInMin = pickedDateInMillis / 60000

            val selectedMonths = selectedMonth + 1

            // Set selected date
//            selectedDate.text = "$selectedMonths/$selectedDay/$selectedYear"
            selectedDate.text = getString(R.string.selected_date_from_calendar, selectedMonths, selectedDay, selectedYear)

            // Set Minutes
            minutes.text = (currentDateInMin - pickedDateInMin).toString()
        }, currentYear, currentMonth, currentDay)

        dialog.datePicker.maxDate = System.currentTimeMillis()
        dialog.show()
    }
}