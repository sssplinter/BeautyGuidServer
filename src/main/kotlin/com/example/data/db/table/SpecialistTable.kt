package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SpecialistTable : IntIdTable("specialist", "specialist_id") {
    val salonId = reference(
        "salon_id",
        SalonTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val firstName = varchar("specialist_first_name", length = 255)
    val lastName = varchar("specialist_last_name", length = 255)
    val categoryId = reference(
        "category_id",
        CategoryTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val photoUrl = varchar("specialist_photo_url", length = 500)
    val rating = double("specialist_rating")
    val marksCount = integer("specialist_count")
}