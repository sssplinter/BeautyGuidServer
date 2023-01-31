package com.example.plugins

import com.example.dao.dao
import com.example.features.login.ArticlesResponseRemote
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        route("articles") {
            get {
                call.respond(ArticlesResponseRemote(dao.allArticles()))
            }
            post {
                val newArticleReq = call.receive<NewArticle>()
                val article = dao.addNewArticle(newArticleReq.title, newArticleReq.body)
                call.respondRedirect("/articles/${article?.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(ArticlesResponseRemote(listOf(dao.article(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(ArticlesResponseRemote(listOf(dao.article(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        dao.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }

                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}
