package com.example.routing.specialist

import kotlinx.serialization.Serializable

@Serializable
data class SpecialistPreview (
    val id: Int,
    val salonId: Int,
    val salonName: String,
    val firstName: String,
    val lastName: String,
    val category: String,
    val photoUrl: String,
    val rating: Double,
    val marksCount: Int
)