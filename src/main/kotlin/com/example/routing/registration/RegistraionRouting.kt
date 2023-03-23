package com.example.routing.registration

import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.data.models.User
import com.example.data.models.UserCredentials
import com.example.routing.login.LoginRequest
import com.example.routing.login.LoginResponse
import com.example.security.hashing.HashingService
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registrationRouting(
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig,
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

        val user = User()
        val userWasAcknowledged = userDao.insertUser(user)

        if (userWasAcknowledged == null) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        val userCredentials = UserCredentials(
            userName = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            userId = userWasAcknowledged.id
        )
        val credentialsWasAcknowledged = userCredentialsDao.insertUserCredentials(userCredentials)

        if (!credentialsWasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = userWasAcknowledged.id.toString()
            ),
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = LoginResponse(
                token = token
            )
        )
    }
}