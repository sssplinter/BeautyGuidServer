package com.example.routing.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
