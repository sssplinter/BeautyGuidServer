package com.example.security.token

data class TokenClaim( //для сохранения ключ-значения в токене
    val name: String,
    val value: String
)
