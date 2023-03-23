package com.example.routing.login

import com.example.data.db.dao.UserCredentialsDao
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.loginRouting(
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig,
    userCredentialsDao: UserCredentialsDao
) {
    post("login") {
        val request = runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val userCredentials = userCredentialsDao.fetchCredentialsByUsername(request.username)
        if (userCredentials == null ) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username")
            return@post
        }

        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = userCredentials.password,
                salt = userCredentials.salt
            )
        )
        if (!isValidPassword) {
            call.respond(HttpStatusCode.Conflict, "Incorrect password")
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = userCredentials.userId.toString()
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