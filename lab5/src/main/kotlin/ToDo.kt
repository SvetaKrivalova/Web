import java.time.LocalDate

class ToDo(private var todoList: MutableList<ToDoItem> = mutableListOf()) {
    fun listOutPut(): List<ToDoItem> = todoList

    fun addItem(item: ToDoItem) = todoList.add(item)

    fun getId(id: Int): ToDoItem? = todoList.find { it.id == id }
    fun getDesc(desc: String): ToDoItem? = todoList.find { it.desc.lowercase().contains(desc.lowercase()) || it.subs.any { sub -> sub.desc.lowercase().contains(desc.lowercase()) } }

    fun deleteId(id: Int): Boolean {
        val item = todoList.find { it.id == id }
        if (item != null) {
            todoList.remove(item)
            return true
        } else return false
    }
    fun deleteAll() = todoList.clear()

    fun updateItem(id: Int, newDesc: String, newStatus: Status, newDate: LocalDate, newDop: String): Boolean {
        val item = todoList.find { it.id == id }
        if (item != null) {
            item.desc = newDesc
            item.status = newStatus
            item.date = newDate
            item.dopInfo = newDop
            return true
        } else return false
    }
}