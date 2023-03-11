package com.example.midtermconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinnerConversionFrom: Spinner
    private lateinit var spinnerConversionTo: Spinner
    private lateinit var editTextValueTo: EditText
    private lateinit var editTextValueFrom: EditText
    private var value = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextValueTo = findViewById(R.id.editTextFinalValue)
        editTextValueFrom = findViewById(R.id.editTextInitialValue)

        // Define spinnerConversionFrom, spinnerConversionTo, editTextValueTo, editTextValueFrom, and value as instance variables
        spinnerConversionFrom = findViewById(R.id.spinnerFrom)
        val adapter = ArrayAdapter.createFromResource(this, R.array.conversion_unit, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerConversionFrom.adapter = adapter

        // Implemented the textwatcher class to display the result of the conversion automatically.
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    when (spinnerConversionFrom.selectedItemPosition) {
                        0 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s m", (value * 1000).toString()))
                        }
                        1 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s ft", (value / 12).toString()))
                        }
                        2 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s F", ((value * 9 / 5) + 32).toString()))
                        }
                        3 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s mm", (value * 10).toString()))
                        }
                        4 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s kg", (value * 0.45).toString()))
                        }
                        5 -> {
                            value = editTextValueFrom.text.toString().toDouble()
                            editTextValueTo.setText(String.format("%s in", (value * 0.39).toString()))
                        }
                    }
                } catch (nfe: NumberFormatException) {
                    value = 0.0
                    editTextValueTo.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }


        editTextValueFrom.addTextChangedListener(textWatcher)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinnerConversionTo.onItemSelectedListener = this
        spinnerConversionFrom.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
