package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable

object CategoryTable : IntIdTable("category", "category_id") {
    val name = varchar("category_name", length = 255)
}