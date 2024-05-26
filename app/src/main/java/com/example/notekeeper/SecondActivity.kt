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
        // if our activity has been destroyed & then recreated, we can use "savedInstanceState" to
        // read back the state we saved in "onSaveInstanceState"
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

        // retrieving the note position from Saved Instance State (if this activity was destroyed & then recreated)
        // or from Intent Extra (if this is the initial creation of the activity)
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET)
            ?: intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
        // if the intent that starts this activity has passed position, it means user want to edit an existing note
        // if no position is passed in Intent Extra, user wants to add a new note
        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            // adding a new empty note to the end of "notes"
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // saving notePosition inside the bundle so that it can be retrieved when this activity is active again
        outState.putInt(NOTE_POSITION, notePosition)
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

        // to change menu items at runtime, invalidateOptionsMenu is used
        invalidateOptionsMenu() // each time user moves from one next to next, onPrepareOptionsMenu will get called
    }

    // this method is automatically called before menu is displayed
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // if we have reached the last note, change "Next" button on Options Menu
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.baseline_block_24_white)
                menuItem.isEnabled = false
            }
        }
        // setting the color of menu options text to white
        val nextItem = menu?.findItem(R.id.action_next)
        val s = SpannableString(nextItem?.title)
        s.setSpan(TextAppearanceSpan(this, R.style.MenuItemTextStyle), 0, s.length, 0)
        nextItem?.title = s
        return super.onPrepareOptionsMenu(menu)
    }

    // to save any changes in activity's content (called when activity moves out of the foreground)
    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        var note = DataManager.notes[notePosition]

        val textNoteTitle: TextView = findViewById(R.id.textNoteTitle)
        note.title = textNoteTitle.text.toString()

        val textNoteText: TextView = findViewById(R.id.textNoteText)
        note.text = textNoteText.text.toString()

        val spinnerCourses: Spinner = findViewById(R.id.spinnerCourses)
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
}