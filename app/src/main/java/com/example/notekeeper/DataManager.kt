package com.example.notekeeper

// we want DataManager to be a Singleton so that both our NoteListActivity & the activity we use for
// editing our notes can all work against the same instance of our data
object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    // initializer block: runs whenever an instance of the class is created
    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)

        course =
            CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes() {
        var note = NoteInfo(
            courses["android_intents"]!!,
            "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime"
        )
        notes.add(note);

        note = NoteInfo(
            course = courses["android_intents"]
                ?: throw IllegalArgumentException("Course not found"),
            title = "Delegating intents",
            text = "PendingIntents are powerful; they delegate much more than just a component invocation"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["android_async"] ?: throw IllegalArgumentException("Course not found"),
            title = "Service default threads",
            text = "Did you know that by default an Android Service will tie up the UI thread?"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["android_async"] ?: throw IllegalArgumentException("Course not found"),
            title = "Long running operations",
            text = "Foreground Services can be tied to a notification icon"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["java_lang"] ?: throw IllegalArgumentException("Course not found"),
            title = "Parameters",
            text = "Leverage variable-length parameter lists"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["java_lang"] ?: throw IllegalArgumentException("Course not found"),
            title = "Anonymous classes",
            text = "Anonymous classes simplify implementing one-use types"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["java_core"] ?: throw IllegalArgumentException("Course not found"),
            title = "Compiler options",
            text = "The -jar option isn't compatible with the -cp option"
        )
        notes.add(note)

        note = NoteInfo(
            course = courses["java_core"] ?: throw IllegalArgumentException("Course not found"),
            title = "Serialization",
            text = "Remember to include SerialVersionUID to assure version compatibility"
        )
        notes.add(note)
    }
}