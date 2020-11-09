package com.example.suntimes

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import com.luckycatlabs.sunrisesunset.dto.Location
import java.util.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.editTextLong).setText(long)
        findViewById<EditText>(R.id.editTextLat).setText(lat)

        findViewById<AutoCompleteTextView>(R.id.editTimeZone).setText(timeZone)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this,  R.layout.support_simple_spinner_dropdown_item, timeZones)

        val textView: AutoCompleteTextView = findViewById(R.id.editTimeZone)

        textView.setAdapter(adapter)

        findViewById<Button>(R.id.newCoordinates_button).setOnClickListener{

            long = findViewById<EditText>(R.id.editTextLong).text.toString()
            lat = findViewById<EditText>(R.id.editTextLat).text.toString()
            timeZone = findViewById<AutoCompleteTextView>(R.id.editTimeZone).text.toString()
            setSunTimes()
        }
        setSunTimes()
    }

    fun setSunTimes() {

        val sunrise = findViewById<TextView>(R.id.textView_sunrise)
        val sunset = findViewById<TextView>(R.id.textView_sunset)
        val calculator = getSunCalculator(MainActivity.long, MainActivity.lat)

        sunrise.text = "Sunrise: ${calculator.getOfficialSunriseForDate(Calendar.getInstance())}"
        sunset.text = "Sunset: ${calculator.getOfficialSunsetForDate(Calendar.getInstance())}"

    }

    companion object {

        public var long: String = "47.3881"
        public var lat: String = "8.7518"
        var timeZone: String = "CET"

        var timeZones: ArrayList<String> = arrayListOf("CEST", "CET")

        fun getSunCalculator(long: String, lat: String): SunriseSunsetCalculator {

            val location = Location(long, lat)
            val calculator = SunriseSunsetCalculator(location, timeZone)
            return calculator
        }
    }

}