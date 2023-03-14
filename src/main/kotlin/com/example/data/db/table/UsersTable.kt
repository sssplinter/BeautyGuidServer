package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users", "user_id"){
    val firstName = varchar("user_first_name", length = 255)
    val lastName = varchar("user_last_name", length = 255)
    val mobileNumber = varchar("user_mobile_number", length = 15)
    val email = varchar("user_email", length = 255)
}