package com.example

import com.example.database.DatabaseFactory
import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegisterRouting
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*


fun main() {

    DatabaseFactory.init()

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
}
