package com.example.notekeeper

import android.os.Bundle
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Set the title of the toolbar
        supportActionBar?.title = "Edit Note"

        // creating the adapter
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        // we need to provide a 2nd layout resource for Spinner
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // associate the adapter with the Spinner
        val spinnerCourses: Spinner = findViewById(R.id.spinnerCourses)
        spinnerCourses.adapter = adapterCourses

        // retrieving the note position from Intent Extra
        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        // if the intent that starts this activity has passed position, it means user want to edit an existing note
        // if no position is passed in Intent Extra, user wants to add a new note
        if (notePosition != POSITION_NOT_SET)
            displayNote()
    }

    private fun displayNote() {
        // retrieving the clicked note from DataManager
        val note = DataManager.notes[notePosition]

        // displaying values within the views on our activity
        val textNoteTitle: TextView = findViewById(R.id.textNoteTitle)
        textNoteTitle.setText(note.title)

        val textNoteText: TextView = findViewById(R.id.textNoteText)
        textNoteText.setText(note.text)

        // select the matching course within the Spinner
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        val spinnerCourses: Spinner = findViewById(R.id.spinnerCourses)
        spinnerCourses.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true

            R.id.action_next -> {
                moveNext()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // setting the color of menu options text to white
        val nextItem = menu.findItem(R.id.action_next)
        val s = SpannableString(nextItem.title)
        s.setSpan(TextAppearanceSpan(this, R.style.MenuItemTextStyle), 0, s.length, 0)
        nextItem.title = s
        return super.onPrepareOptionsMenu(menu)
    }
}