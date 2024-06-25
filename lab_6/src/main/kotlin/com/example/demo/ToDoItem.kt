import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "web_todo")
class ToDoItem(
    @Id
    var id: String = ObjectId().toString(),
    var desc: String,
    var status: String = "ACTIVE",
    var dueDate: String = LocalDate.now().toString(),
    var dopInfo: String = "",
    val subs: MutableList<ToDoItem> = mutableListOf()) {

    fun addSub(sub: ToDoItem) {
        subs.add(sub)
    }
}
