import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.LocalDate

fun main() {
    val toDo = ToDo()

    while (true) {
        println("1. Весь список задач")
        println("2. Добавить задачу")
        println("3. Добавить подзадачу")
        println("4. Получить задачу по описанию")
        println("5. Обновить задачу")
        println("6. Удалить задачу")
        println("7. Очистить список")

        when (val input = readlnOrNull()?.toIntOrNull()) {
            1 -> {
                toDo.listOutPut().forEach { println(it) }
                println("\n\n")
            }
            2 -> {
                println("Описание задачи: ")
                val desc = readlnOrNull() ?: ""
                val status = Status.ACTIVE
                val date = LocalDate.now()
                println("Дополнительная информация: ")
                val dop = readlnOrNull() ?: "\n"

                val item = ToDoItem(desc, status, date, dop)
                toDo.addItem(item)
                println("\n\n")
            }
            3 -> {
                println("Номер задачи: ")
                val baseItemId = readlnOrNull()?.toIntOrNull() ?: -1
                if (baseItemId != -1) {
                    val baseItem = toDo.getId(baseItemId)
                    if (baseItem != null) {
                        println("Описание подзадачи: ")
                        val desc = readlnOrNull() ?: ""
                        val status = Status.ACTIVE
                        val date = LocalDate.now()
                        println("Дополнительная информация: ")
                        val dop = readlnOrNull() ?: ""

                        val subItem = ToDoItem(desc, status, date, dop)
                        baseItem.addSub(subItem)
                        println("\n\n")
                    }
                }



            }
            4 -> {
                println("Описание задачи: ")
                val keyword = readlnOrNull() ?: ""
                val findItem = toDo.getDesc(keyword)
                if (findItem != null) {
                    println(findItem)
                }
                println("\n\n")
            }
            5 -> {
                println("Номер задачи: ")
                val itemId = readlnOrNull()?.toIntOrNull() ?: -1
                if (itemId != -1) {
                    println("Описание задачи: ")
                    val newDesc = readlnOrNull() ?: ""
                    val newStatus = Status.ACTIVE
                    val newDate = LocalDate.now()
                    println("Дополнительная информация: ")
                    val newDop = readlnOrNull() ?: "\n"

                    toDo.updateItem(itemId, newDesc, newStatus, newDate, newDop)
                    println("\n\n")
                }
            }
            6 -> {
                println("Номер задачи: ")
                val itemId = readlnOrNull()?.toIntOrNull() ?: -1
                if (itemId != -1) {
                    toDo.deleteId(itemId)
                    println("\n\n")
                }
            }
            7 -> {
                toDo.deleteAll()
            }

            else -> {
                println("\n\n")
                println("Введите номер от 1 до 7")
            }
        }
    }
}