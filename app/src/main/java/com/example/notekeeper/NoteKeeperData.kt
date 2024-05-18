package com.example.notekeeper

class CourseInfo(val courseId: String, val title: String) {
    // when instances of this class are displayed within a Spinner,
    // we need to indicate what value from the instance we want displayed
    // for that, we need to override the toString() method
    override fun toString(): String {
        return title
    }
}

class NoteInfo(var course: CourseInfo, var title: String, var text: String)