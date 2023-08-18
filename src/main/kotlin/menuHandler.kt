import java.util.Scanner

class MenuHandler {
    private val scanner = Scanner(System.`in`)
    private val listArchives: MutableList<Archive> = mutableListOf()
    private var selectMenuArchive =0
    private var selectMenuNote =0
    fun menuSelection(choice: Screen) {
        when (choice) {
            is Screen.MainMenu -> mainMenu()
            is Screen.ArchiveCreateMenu -> createNewArchive()
            is Screen.ArchiveMenu -> openArchive(selectMenuArchive)
            is Screen.NoteCreateMenu -> createNewNote(listArchives[selectMenuArchive-1])
            is Screen.NoteMenu -> openNote(listArchives[selectMenuArchive-1],selectMenuNote-1)
        }
    }

    private fun openArchive(pos: Int) {
        while (true) {
            println("0. Создать заметку")
            for ((index, notes) in listArchives[pos-1].listNotes.withIndex()) {
                println("${index + 1}. ${notes.textNote}")
            }
            println("${listArchives[pos-1].listNotes.size+1}. Назад")
            selectMenuNote = getTheCommandForList(listArchives[pos-1].listNotes)
            when (selectMenuNote) {
                0 -> menuSelection(Screen.NoteCreateMenu)
                listArchives[pos-1].listNotes.size+1 -> break
                else -> menuSelection(Screen.NoteMenu)
            }
        }
    }

    private fun openNote(archive: Archive,pos: Int){
        while (true){
            println (archive.listNotes[pos].textNote)
            println("1. Назад")
            if (getTheCommandForList(archive.listNotes)==1) break
        }
    }

    private fun createNewNote(archive: Archive) {
        println("Введите имя новую заметку")
        val note = scanner.nextLine()
        archive.addNote(note)
    }

    private fun showMainMenu() {
        println("0. Создать архив")
        for ((index, archives) in listArchives.withIndex()) {
            println("${index + 1}. ${archives.nameArchive}")
        }
        println("${listArchives.size + 1}. Выход")
    }

    private fun <T> getTheCommandForList(listNum: MutableList<T>): Int {
        while (true) {
            try {
                val command = scanner.nextLine().toInt()
                if (command < listNum.size + 2 && command >= 0) {
                    return command
                } else println("Введите корректную команду")
            } catch (e: Exception) {
                println("Необходимо указать пункт меню цифрой")
            }
        }
    }

    private fun mainMenu() {
        while (true) {
            showMainMenu()
            selectMenuArchive = getTheCommandForList(listArchives)
            when (selectMenuArchive) {
                0 -> menuSelection(Screen.ArchiveCreateMenu)
                listArchives.size + 1 -> return
                else -> {
                    println("Архив ${listArchives[selectMenuArchive-1].nameArchive} открыт")
                    menuSelection(Screen.ArchiveMenu)
                }
            }
        }
    }

    private fun createNewArchive() {
        println("Введите имя нового архива")
        val nameArchive = scanner.nextLine()
        listArchives.add(Archive(nameArchive))
    }
}