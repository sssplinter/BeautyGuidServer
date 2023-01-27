package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class NewArticle(
    val title: String,
    val body: String
)