package com.zuhlke.quarkus

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoCompanion
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity
import java.time.ZonedDateTime


@MongoEntity(collection = "todo")
data class Todo(
        var title: String = "",
        var saveDate: ZonedDateTime = ZonedDateTime.now(),
        var items: List<TodoItem> = listOf(),
        var version: Int = 0
): PanacheMongoEntity() {
    companion object: PanacheMongoCompanion<Todo> {
        fun findTodo(title: String): Todo? {
            return find("title", title).firstResult()
        }
    }

    fun saveTodo() {
        persist()
    }
}

data class TodoItem( var description: String = "")


