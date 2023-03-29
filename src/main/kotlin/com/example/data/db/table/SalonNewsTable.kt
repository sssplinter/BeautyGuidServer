package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SalonNewsTable : IntIdTable("salon_news", "salon_news_id") {
    val salonId = reference(
        "salon_id",
        SalonTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val specialistId = reference(
        "specialist_id",
        SpecialistTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val photoUrl = varchar("salon_news_photo", 500)
    val description = varchar("salon_news_description", length = 500)
}