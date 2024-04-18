import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val ctx: ApplicationContext = AnnotationConfigApplicationContext(ToDoConfig::class.java)
    var toDo: ToDo = ctx.getBean(ToDo::class.java)

    toDo.add(ToDoItem("Помыть посуду", Status.ACTIVE))
    toDo.add(ToDoItem("Убраться", Status.ACTIVE))
    toDo.add(ToDoItem("Погулять с собакой", Status.DONE))
    toDo.add(ToDoItem("Отдохнуть", Status.ACTIVE))

    toDo.listOutPut(toDo.listToDo())
}