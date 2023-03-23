package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SalonWorkingHoursTable : IntIdTable("salon_working_hours", "working_hours_id") {
    val salonId = reference(
        "salon_id", SalonTable.id, onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val day = varchar("day", 255)
    val time = varchar("day", 255)
}