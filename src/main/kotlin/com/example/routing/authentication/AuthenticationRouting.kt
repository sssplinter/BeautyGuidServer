package com.example.routing.authentication

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticationRouting() {
    //проверят остается ли токен валидным
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }

        get("secret") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            call.respond(
                status = HttpStatusCode.OK,
                message = "Your user id: $userId"
            )
        }
    }
}