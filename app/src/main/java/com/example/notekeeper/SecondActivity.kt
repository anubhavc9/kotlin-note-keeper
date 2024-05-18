package com.example.notekeeper

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val dm = DataManager()
        // creating the adapter
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            dm.courses.values.toList()
        )
        // we need to provide a 2nd layout resource for Spinner
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // associate the adapter with the Spinner
        val spinnerCourses: Spinner = findViewById(R.id.spinnerCourses)
        spinnerCourses.adapter = adapterCourses
    }
}