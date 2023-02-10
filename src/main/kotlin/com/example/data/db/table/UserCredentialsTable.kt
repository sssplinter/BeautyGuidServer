package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object UserCredentialsTable : IntIdTable("user_credentials", "user_credentials_id") {
    val username = reference("user_username",
        UsersTable.username,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE)
    val password = varchar("user_credentials_password_hash", length = 255)
    val salt = varchar("user_credentials_salt", length = 255)
}