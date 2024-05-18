package com.example.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val textView: TextView = findViewById(R.id.textDisplayedValue)

        fab.setOnClickListener{
            // multiply the value by 2 each time FAB is clicked
            val originalValue = textView.text.toString().toInt()
            val newValue = originalValue * 2
            textView.text = newValue.toString()
            Snackbar.make(it, "Value $originalValue changed to $newValue",Snackbar.LENGTH_LONG).show()
        }
    }

}