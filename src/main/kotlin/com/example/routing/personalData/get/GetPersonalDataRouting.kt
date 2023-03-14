package com.example.routing.personalData.get

import com.example.data.db.dao.UsersDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getPersonalDataRouting(
    userDao: UsersDao
) {
    authenticate {
        get("getPersonalData") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", Int::class)

            if (userId == null) {
                call.respond(HttpStatusCode.Conflict, "SavePersonalData: Incorrect userId")
                return@get
            }

            val user = userDao.fetchUserById(userId)
            if (user == null) {
                call.respond(HttpStatusCode.Conflict)
                return@get
            }

            call.respond(
                status = HttpStatusCode.OK,
                message = user
            )
        }
    }
}