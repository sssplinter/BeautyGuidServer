package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var mobileNumber: String = "",
    var email: String = ""
)
