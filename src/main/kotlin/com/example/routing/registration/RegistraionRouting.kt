package com.example.routing.registration

import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.data.models.User
import com.example.data.models.UserCredentials
import com.example.routing.login.LoginRequest
import com.example.routing.routing
import com.example.security.hashing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registrationRouting(
    hashingService: HashingService,
    userDao: UsersDao,
    userCredentialsDao: UserCredentialsDao
) {
    post("/register") {
        val request = runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        val fieldsBlank = request.username.isBlank() || request.password.isBlank()
        val isPasswordShort = request.password.length < 8
        if (fieldsBlank || isPasswordShort) {
            call.respond(HttpStatusCode.Conflict, "Error message")
            return@post
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)
        val user = User(
            username = request.username,
        )
        val userCredentials = UserCredentials(
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        //проверка на то что норм удалось добавить в бд
        //запихнуть бы под одну транзакцию
        val userWasAcknowledged = userDao.insertUser(user)
        val credentialsWasAcknowledged = userCredentialsDao.insertUserCredentials(user.username, userCredentials)
        if (!userWasAcknowledged || !credentialsWasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        call.respond(HttpStatusCode.OK)
    }
}