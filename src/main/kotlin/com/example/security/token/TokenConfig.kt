package com.example.security.token

data class TokenConfig(
    val issuer: String,
    val audience: String, // определяет тип юзера тут определяеть нормал или админ
    val expiresIn: Long,
    val secret: String
)
