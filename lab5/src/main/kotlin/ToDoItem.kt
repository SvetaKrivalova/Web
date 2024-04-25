import java.time.LocalDate

class ToDoItem(var desc: String, var status: Status, var date: LocalDate, var dopInfo: String) {
    companion object { private var idCounter = 1 }
    val id: Int = idCounter++
    val subs: MutableList<ToDoItem> = mutableListOf()

    fun addSub(sub: ToDoItem) {
        subs.add(sub)
    }

    override fun toString(): String {
        val subString = if (subs.isNotEmpty()) {
            val subDetails = subs.joinToString("\n") { sub ->
                    """
                |   Номер задачи: ${sub.id}
                |   Описание: ${sub.desc}
                |   Дополнительная информация: ${sub.dopInfo}
                """.trimMargin()
            }
            "Подзадачи:\n$subDetails"
        } else ""
        return """
            Номер: $id
            Описание: $desc
            Статус: $status
            Дата: $date
            Доп. информация: $dopInfo
        """.trimIndent()
    }
}

enum class Status {
    DONE, ACTIVE
}
