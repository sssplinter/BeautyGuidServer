package com.example.data.models

data class SalonInfo(
    val salonId: Int,
    val address: String,
    val phoneNumber: String?,
    val email: String?,
    val webLink: String?,
    val telegramLink: String?,
    val instagramLink: String?,
    val vkLink: String?,
    val workingHoursId: Int
)
