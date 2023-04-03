package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SalonNews(
    val newsId: Int,
    val specialistId: Int,
    val specialistFirstName: String,
    val specialistLastName: String,
    val photoUrl: String,
    val description: String
)
