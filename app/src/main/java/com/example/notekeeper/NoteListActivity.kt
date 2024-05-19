package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton3)
        fab.setOnClickListener {
            val activityIntent = Intent(this, SecondActivity::class.java)
            startActivity(activityIntent)
        }

        // populating a list is similar to populating a Spinner (using an Adapter)
        val listNotes: ListView = findViewById(R.id.listNotes)
        listNotes.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)
    }
}