package com.example.routing.personalData.save

import kotlinx.serialization.Serializable

@Serializable
data class PersonalDataRequest(
    val firstName: String,
    val lastName: String,
    val mobileNumber: String,
    val email: String
)