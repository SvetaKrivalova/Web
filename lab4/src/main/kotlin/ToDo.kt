class ToDo(private var todoList: MutableList<ToDoItem> = mutableListOf()) {
    fun add(item: ToDoItem) = todoList.add(item)
    fun delete(desc: String): Boolean = todoList.removeIf { it.desc.equals((desc))}
    fun deleteActive(): Boolean = todoList.removeIf { it.status.equals(Status.ACTIVE) }
    fun deleteDone(): Boolean = todoList.removeIf { it.status.equals(Status.DONE) }
    fun deleteAll() = todoList.clear()
    private fun find(desc: String): ToDoItem? = todoList.find { it.desc.equals(desc) }
    fun updateDesc(descOld: String, descNew: String, statusNew: Status): Boolean {
        var item = find(descOld)
        if (item != null && descNew != null) {
            todoList.set(todoList.indexOf(item), item.apply { this.desc = descNew })
            return true
        } else return false
    }
    fun updateStatus(desc: String, status: Status): Boolean {
        var item = find(desc)
        if (item != null) {
            todoList.set(todoList.indexOf(item), item.apply { this.status = status })
            return true
        } else return false
    }
    fun listToDo(status: Status? = null): List<ToDoItem> = when(status) {
        Status.ACTIVE -> todoList.filter { it.status.equals(Status.ACTIVE) }
        Status.DONE -> todoList.filter { it.status.equals(Status.DONE) }
        null -> todoList
    }

}