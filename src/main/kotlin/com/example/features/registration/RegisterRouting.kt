package com.example.features.registration

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val receive = call.receive(RegisterReceiveRemote::class)

            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect email")
            }

            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            val generatedToken = "Test token"//java.util.UUID.randomUUID().toString()
            InMemoryCache.userList.add(receive)
            InMemoryCache.tokenList.add(TokenCache(login = receive.login, token = generatedToken))

            call.respond(RegisterResponseRemote(token = generatedToken))
        }
    }
}