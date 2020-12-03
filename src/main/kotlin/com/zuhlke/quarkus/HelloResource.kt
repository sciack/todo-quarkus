package com.zuhlke.quarkus

import io.quarkus.panache.common.Sort
import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import org.bson.types.ObjectId
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Counted
import org.eclipse.microprofile.metrics.annotation.Timed
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/")
class HomePage {
    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }
    @Inject
    lateinit var home: Template

    @Inject
    lateinit var todoPage: Template

    @GET
    @Timed(name= "homePageResponse", unit= MetricUnits.MILLISECONDS)
    @Counted(name= "homePageCall")
    @Produces(MediaType.TEXT_HTML)
    fun hello():TemplateInstance {
        val sort = Sort.descending("saveDate")
        val todos = Todo.findAll(sort).stream()
        return home.data("todos", todos)
    }

    @GET
    @Path("/todo")
    @Produces(MediaType.TEXT_HTML)
    fun todo():TemplateInstance {
        return todoPage.data("todo", Todo())
    }

    @PUT
    @Path("/todo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun save(todo:Todo):Todo {
        todo.saveTodo()
        return todo
    }

    @GET
    @Path("/todo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    fun save(@PathParam("id") id: String): TemplateInstance {
        val todo = Todo.findById(ObjectId(id))
        logger.info("Todo:{}", todo)
        return todoPage.data("todo", todo)
    }

    @PUT
    @Path("/todo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: String, todo: Todo): Todo {
        todo.id = ObjectId(id)
        logger.info("Todo:{}", todo)
        todo.persistOrUpdate()
        return todo
    }
}