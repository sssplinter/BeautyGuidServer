package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Salon(
    val id: Int,
    val photoUrl: String,
    val name: String,
    val description: String,
    val rating: Double
)