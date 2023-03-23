package com.example.data.models

data class UserCredentials(
    val userId: Int,
    val userName: String,
    val password: String,
    val salt: String
)
