package com.example.demo

import ToDoItem
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.LocalDate

@SpringBootApplication
@EnableMongoRepositories
class DemoApplication {
	@Autowired
	lateinit var toDoRepository: ToDoRepository


	@PostConstruct
	fun initRepository() {
		/*repeat(2) {
			toDoRepository.save(ToDoItem(desc = "task $it"))
		}*/
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
