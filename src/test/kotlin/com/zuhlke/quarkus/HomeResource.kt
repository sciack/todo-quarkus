package com.zuhlke.quarkus

import com.mongodb.client.MongoClient
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import javax.inject.Inject

@QuarkusTest
class HomeResource {

    @Inject
    lateinit var mongoClient: MongoClient

    @ConfigProperty(name = "quarkus.mongodb.database")
    lateinit var dbName: String

    @BeforeEach
    fun setup() {
        mongoClient.getDatabase(dbName).drop()
    }
    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/")
          .then()
             .statusCode(200)
             .body(Matchers.containsString("Todo"))
    }

    @Test
    fun `should show the todo in the table`() {
        Todo("First todo", saveDate = ZonedDateTime.now(), items = listOf(TodoItem("an Item"))).saveTodo()
        given()
                .`when`().get("/")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("<td>First todo</td>"))
    }

    
}