package eu.tutorials.dateofbirth

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelected : TextView? = null
    private var tvAgeinminutes:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelected = findViewById(R.id.tvSelectedDate)
        tvAgeinminutes =findViewById(R.id.AgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        val dpd =DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { view, Selectedyear, Selectedmonth, Selecteddayofmonth ->
                Toast.makeText(this, "Year Selected is $Selectedyear,Month Selected is ${Selectedmonth+1}, Day of Month Selected is $Selecteddayofmonth",Toast.LENGTH_LONG).show()
                val SelecteDate = "$Selecteddayofmonth/${Selectedmonth+1}/$Selectedyear"
                tvSelected?.text = SelecteDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(SelecteDate)
                theDate?.let {
                    val SelectedDateInMInutes = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateinMinutes = currentDate.time/60000
                        val differenceinMinutes = currentDateinMinutes - SelectedDateInMInutes

                        tvAgeinminutes?.text = differenceinMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}