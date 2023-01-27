package com.example.features.login

import com.example.models.Article
import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val login: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)

@Serializable
data class ArticlesResponseRemote(
    val articles: List<Article?>
)
