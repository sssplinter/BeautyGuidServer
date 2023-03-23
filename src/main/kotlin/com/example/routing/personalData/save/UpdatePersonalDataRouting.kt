package com.example.routing.personalData.save

import com.example.data.db.dao.UsersDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updatePersonalData(userDao: UsersDao) {
    authenticate {
        post("updatePersonalData") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", Int::class)

            if (userId == null) {
                call.respond(HttpStatusCode.Conflict, "SavePersonalData: Incorrect userId")
                return@post
            }

            val user = userDao.fetchUserById(userId = userId)
            if (user == null) {
                call.respond(HttpStatusCode.Conflict, "SavePersonalData: No such user")
                return@post
            }

            val request = runCatching { call.receiveNullable<PersonalDataRequest>() }.getOrNull() ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            user.apply {
                firstName = request.firstName
                lastName = request.lastName
                mobileNumber = request.mobileNumber
                email = request.email
            }

            userDao.updateUserById(user)

            call.respond(
                status = HttpStatusCode.OK,
                message = "Personal data updated successfully"
            )
        }
    }
}