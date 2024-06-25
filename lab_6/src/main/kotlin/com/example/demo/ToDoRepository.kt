package com.example.demo

import ToDoItem
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ToDoRepository : MongoRepository<ToDoItem, String> {
    fun findByDescContainingIgnoreCase(desc: String): List<ToDoItem>
}
