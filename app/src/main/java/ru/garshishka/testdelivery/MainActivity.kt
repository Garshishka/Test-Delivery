package ru.garshishka.testdelivery

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindSpinner()
        setAppBarInteraction()
    }

    private fun bindSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.location,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            findViewById<Spinner>(R.id.dropdown_location).adapter = adapter
        }
    }

    private fun setAppBarInteraction(){
        findViewById<ImageButton>(R.id.qr_code_button).setOnClickListener {
            Toast.makeText(this,R.string.qr_code_pressed,Toast.LENGTH_SHORT).show()
        }
    }
}