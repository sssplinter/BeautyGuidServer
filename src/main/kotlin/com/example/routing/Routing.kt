package com.example.routing

import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.plugins.generateTokenConfig
import com.example.routing.authentication.authenticationRouting
import com.example.routing.login.loginRouting
import com.example.routing.registration.registrationRouting
import com.example.security.hashing.HashingService
import com.example.security.hashing.SHA256HashingService
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.routing(
    tokenConfig: TokenConfig
) {
    val tokenService = JwtTokenService()
    val hashingService = SHA256HashingService()

    val userDao = UsersDao()
    val userCredentialsDao = UserCredentialsDao()

    routing {
        authenticationRouting()
        registrationRouting(hashingService, userDao, userCredentialsDao)
        loginRouting(hashingService, tokenService, tokenConfig, userDao, userCredentialsDao)
    }

}