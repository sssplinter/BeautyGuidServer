package com.example.data.models

data class Specialist(
    val id: Int,
    val salonId: Int,
    val firstName: String,
    val lastName: String,
    val categoryId: Int,
    val photoUrl: String,
    val rating: Double,
    val marksCount: Int
)
