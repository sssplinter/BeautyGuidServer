package com.example.data.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SalonMarkTable : Table("salon_mark") {
    val userId = reference(
        "user_id", UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val salonId = reference(
        "salon_id", SpecialistTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    override  val primaryKey = PrimaryKey(salonId, userId, name = "composite_salon_mark_id")
    val mark = double("mark")
}