package com.example.sample

import kotlinx.serialization.Serializable

@Serializable
data class NewArticle(
    val title: String,
    val body: String
)