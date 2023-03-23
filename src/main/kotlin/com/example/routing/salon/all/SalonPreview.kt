package com.example.routing.salon.all

import kotlinx.serialization.Serializable

@Serializable
data class SalonPreview (
    val salonId: Int,
    val salonName: String,
    val salonPhotoUrl: String,
    val salonDescription: String,
    val rating: Double,
    val location: String,
    val categories: List<String>
)