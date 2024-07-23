package com.example.simpleapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var countryInput: EditText
    private lateinit var countryDisplay: TextView
    private val countries = mutableListOf("United States", "Canada", "Mexico")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)
        countryInput = findViewById(R.id.countryInput)
        countryDisplay = findViewById(R.id.countryDisplay)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set up the Spinner's onItemSelectedListener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                // Update TextView based on the selected country
                countryDisplay.text = "Selected Country: $selectedCountry"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                countryDisplay.text = "Selected Country: None"
            }
        }

        button.setOnClickListener {
            val newCountry = countryInput.text.toString().trim()
            if (newCountry.isNotEmpty()) {
                countries.add(newCountry)
                (spinner.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                countryInput.text.clear()
                Toast.makeText(this, "Country added: $newCountry", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a country", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
