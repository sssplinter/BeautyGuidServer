package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SalonInfo(
    val salonId: Int,
    val address: String,
    val phoneNumber: String?,
    val email: String?,
    val webLink: String?,
    val telegramLink: String?,
    val instagramLink: String?,
    val vkLink: String?
)
