class Archive(val nameArchive: String) {
    val listNotes: MutableList<Note> = mutableListOf()
    init {
        println("Создан архив с именем $nameArchive")
    }
    fun addNote(note: String) {
        listNotes.add(Note(note))
    }
}