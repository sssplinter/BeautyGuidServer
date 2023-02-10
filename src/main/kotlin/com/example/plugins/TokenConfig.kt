package com.example.plugins

import com.example.security.token.TokenConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

fun Application.generateTokenConfig(): TokenConfig {
    return TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = TimeUnit.DAYS.toMillis(365),
        secret = System.getenv("JWT_SECRET") // не стоит это хранить в гите или в проекте, задается в Edit Configuration -> Environment variables в формате JWT_SECRET=value
    )
}