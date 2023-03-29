package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SalonNewsPreview(
    val newsId: Int,
    val photoUrl: String
)
