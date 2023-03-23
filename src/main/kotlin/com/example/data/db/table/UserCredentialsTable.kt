package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object UserCredentialsTable : IntIdTable("user_credentials", "user_credentials_id") {
    val username = varchar("user_username", length = 255)
    val password = varchar("user_credentials_password_hash", length = 255)
    val userId = reference("user_id",
        UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE)
    val salt = varchar("user_credentials_salt", length = 255)
}