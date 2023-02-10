package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users", "user_id"){
    val username = varchar("user_username", length = 255).uniqueIndex()
}