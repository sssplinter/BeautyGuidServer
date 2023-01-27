package com.example

import com.example.dao.DatabaseFactory
import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegisterRouting
import com.example.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.cio.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    DatabaseFactory.init()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
}
