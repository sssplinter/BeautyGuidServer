package com.example.routing.salon

import kotlinx.serialization.Serializable

@Serializable
data class GetSalonResponse(
    val id: Int,
    val photoUrl: String,
    val name: String,
    val address: String,
    val rating: Double
)