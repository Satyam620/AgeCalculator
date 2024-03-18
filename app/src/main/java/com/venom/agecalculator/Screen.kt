package com.venom.agecalculator

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen() {
    val it= LocalContext.current
    val date=remember{ mutableStateOf("00/00/00")}
    val minutes = remember{ mutableStateOf("0")}

    fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            it,
            { _, sYear, sMonth, dayOfMonth ->
                Toast.makeText(it, "Year Was $year", Toast.LENGTH_LONG).show()
                val selectedDate = "$dayOfMonth/${sMonth+1}/$sYear"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate?.time?.div(60000)

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate?.time?.div(60000)
                val difference = currentDateInMinutes!! - selectedDateInMinutes!!

                minutes.value = difference.toString()
                date.value = selectedDate

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(238, 228, 177)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(67, 10, 93),
            titleContentColor = Color.White,
        ), title = {
            Text("AGE TO MINUTES CALCULATOR", fontWeight = FontWeight.Bold)
        })
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "CALCULATE YOUR",
            fontSize = 20.sp,
            color = Color(95, 55, 75),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            "AGE",
            Modifier.background(Color(140, 106, 93)),
            fontSize = 20.sp,
            color = Color(255, 255, 255),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "IN MINUTES",
            fontSize = 20.sp,
            color = Color(95, 55, 75),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                clickDatePicker()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(116, 105, 182)
            )
        ) {
            Text(
                text = "SELECT DATE",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = date.value,
            fontSize = 20.sp,
            color = Color(95, 55, 75),
            fontWeight = FontWeight.Bold
        )
        Text(text = "Selected Date", fontSize = 20.sp, color = Color(95, 55, 75))

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = minutes.value,
            fontSize = 30.sp,
            color = Color(95, 55, 75),
            fontWeight = FontWeight.Bold
        )
        Text(text = "In minutes till date", fontSize = 20.sp, color = Color(95, 55, 75))
    }
}




@Composable
@Preview(showBackground = true)
fun ScreenPreview() {
    Screen()
}