package com.example.features.login

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
                val generatedToken = UUID.randomUUID().toString()
                InMemoryCache.tokenList.add(TokenCache(login = receive.login, token = generatedToken))
                call.respond(LoginResponseRemote(token = generatedToken))
                return@post
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}