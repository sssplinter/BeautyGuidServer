package com.example

import com.example.data.db.DatabaseFactory
import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.sample.registration.configureRegisterRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.plugins.generateTokenConfig
import com.example.routing.routing
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
//    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
    //Такой старт нужен чтобы работал application.conf, вся настройка тоже в нем
    EngineMain.main(args)
}

@Suppress("Unused")
fun Application.module() {
    DatabaseFactory.init()
    val tokenConfig = generateTokenConfig()

    configureSerialization()
    configureSecurity(tokenConfig)
    routing(tokenConfig)
}

