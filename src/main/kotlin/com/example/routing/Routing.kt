package com.example.routing

import com.example.data.db.dao.SalonDao
import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.routing.authentication.authenticationRouting
import com.example.routing.images.getImage
import com.example.routing.login.loginRouting
import com.example.routing.personalData.get.getPersonalDataRouting
import com.example.routing.personalData.save.updatePersonalData
import com.example.routing.registration.registrationRouting
import com.example.routing.salon.all.getAllSalons
import com.example.routing.salon.getSalonById
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
    val salonDao = SalonDao()

    routing {
        authenticationRouting()
        registrationRouting(hashingService, tokenService, tokenConfig, userDao, userCredentialsDao)
        loginRouting(hashingService, tokenService, tokenConfig, userCredentialsDao)
        updatePersonalData(userDao)
        getPersonalDataRouting(userDao)
        getImage()
        getSalonById(salonDao)
        getAllSalons(salonDao)
    }

}