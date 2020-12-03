package com.zuhlke.quarkus

import io.quarkus.test.junit.QuarkusTest
import org.exparity.hamcrest.date.ZonedDateTimeMatchers.within
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.temporal.ChronoUnit

@QuarkusTest
class TodoTest {

    @AfterEach
    fun clear() {
        Todo.deleteAll()
    }

    @Test
    fun `must persist a todo`() {
        val original = Todo(title = "Test todo", items = listOf(TodoItem("something to do")), version = 0).also {
            it.persist()
        }

        val todo = Todo.findTodo("Test todo")
        assertEquals("Test todo", todo?.title)

        assertThat(original.saveDate, within(1, ChronoUnit.MILLIS, todo?.saveDate))
    }

    @Test
    @Disabled
    fun `must not persist two todo with the same id`() {
        Todo(title = "Test todo", items = listOf(TodoItem("something to do")), version = 0).also {
            it.persist()
        }

        assertThrows<Exception> {
            Todo(title = "Test todo", items = listOf(TodoItem("something to do")), version = 0).also {
                it.persist()
            }
        }


    }
}