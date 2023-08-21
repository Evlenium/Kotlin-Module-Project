class Archive(val nameArchive: String) {
    val listNotes: MutableList<Note> = mutableListOf()
    init {
        println("Создан архив с именем $nameArchive")
    }
    fun addNote(nameNote: String, textNote: String) {
        listNotes.add(Note(nameNote,textNote))
    }
}