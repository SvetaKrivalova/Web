package com.example.demo

import ToDoItem
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Api("ToDo API")
@RestController
class DemoController {
    private val logger: Logger = LoggerFactory.getLogger(DemoController::class.java)
    @Autowired
    lateinit var toDoRepository: ToDoRepository

    @GetMapping("/home")
    fun home(): String = "HOME"

    @ApiOperation("Получить список задач")
    @GetMapping("/tasks")
    fun listOutPut(): ResponseEntity<Any> {
        try {
            val tasks = toDoRepository.findAll()
            return ResponseEntity.ok(tasks)
        } catch (e: Exception) {
            logger.error("Error retrieving tasks", e)
            return ResponseEntity.internalServerError().build()
        }
    }

    @ApiOperation("Добавить задачу")
    @PostMapping("/tasks")
    fun addItem(@RequestBody request: ToDoItem): ResponseEntity<Any> {
        val item = ToDoItem(request.id,request.desc, request.status, request.dueDate, request.dopInfo)
        toDoRepository.save(item)

        return ResponseEntity.ok(item)
    }

    @ApiOperation("Получить задачу по описанию")
    @GetMapping("/tasks/{desc}")
    fun getDesc(@PathVariable desc: String): ResponseEntity<Any> {
        val foundItem = toDoRepository.findByDescContainingIgnoreCase(desc)
        if (foundItem!= null) {
            return ResponseEntity.ok(foundItem)
        } else return ResponseEntity.notFound().build()
    }

    @ApiOperation("Удалить задачу")
    @DeleteMapping("/tasks/{id}")
    fun deleteId(@PathVariable id: String): ResponseEntity<Any> {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id)
            return ResponseEntity.ok().build()
        } else return ResponseEntity.notFound().build()
    }

    @ApiOperation("Удалить список задач")
    @DeleteMapping("/tasks")
    fun deleteAll(): ResponseEntity<Any> {
        return ResponseEntity.ok(toDoRepository.deleteAll())
    }

    @ApiOperation("Обновить задачу")
    @PutMapping("/tasks/{id}")
    fun updateItem(@PathVariable id: String, @RequestBody request: ToDoItem): ResponseEntity<Any> {
        try {
            val item = toDoRepository.findById(id)
            if (item.isPresent) {
                item.get().desc = request.desc
                item.get().status = request.status
                item.get().dueDate = request.dueDate
                item.get().dopInfo = request.dopInfo

                toDoRepository.save(item.get())
                return ResponseEntity.ok(item.get())
            } else {
                return ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            logger.error("Error updating item", e)
            return ResponseEntity.status(500).body("Internal Server Error")
        }
    }

    @ApiOperation("Добавить подзадачу")
    @PostMapping("/tasks/{id}")
    fun addSub(@PathVariable id: String, @RequestBody request: ToDoItem): ResponseEntity<Any> {
        val item = toDoRepository.findById(id)
        if (item.isPresent) {

            val sub = ToDoItem(request.id, request.desc, request.status, request.dueDate, request.dopInfo)
            item.get().subs.add(sub)

            toDoRepository.save(item.get())
            return ResponseEntity.ok(item.get())
        } else return ResponseEntity.notFound().build()
    }
}

