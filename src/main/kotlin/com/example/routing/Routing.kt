package com.example.routing

import com.example.data.db.dao.*
import com.example.routing.authentication.authenticationRouting
import com.example.routing.images.getSalonImage
import com.example.routing.images.getSalonNewsPhotoRouting
import com.example.routing.images.getSpecialistPhoto
import com.example.routing.login.loginRouting
import com.example.routing.personalData.get.getPersonalDataRouting
import com.example.routing.personalData.save.updatePersonalData
import com.example.routing.registration.registrationRouting
import com.example.routing.salon.all.getAllSalons
import com.example.routing.salon.byId.getSalonById
import com.example.routing.salon.info.getSalonInfo
import com.example.routing.salonNews.getSalonNewsListRouting
import com.example.routing.salonNews.getSalonNewsPreviewListRouting
import com.example.routing.specialist.getAllSpecialistsPreviewRouting
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
    val specialistDao = SpecialistDao()
    val salonNewsDao = SalonNewsDao()

    routing {
        authenticationRouting()
        registrationRouting(hashingService, tokenService, tokenConfig, userDao, userCredentialsDao)
        loginRouting(hashingService, tokenService, tokenConfig, userCredentialsDao)
        updatePersonalData(userDao)
        getPersonalDataRouting(userDao)
        getSalonImage()
        getSpecialistPhoto()
        getSalonNewsPhotoRouting()
        getSalonById(salonDao)
        getAllSalons(salonDao)
        getSalonInfo(salonDao)
        getAllSpecialistsPreviewRouting(specialistDao, salonDao)
        getSalonNewsPreviewListRouting(salonNewsDao)
        getSalonNewsListRouting(salonNewsDao)
    }

}