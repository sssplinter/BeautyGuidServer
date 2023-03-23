package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SalonM2MCategoryTable : IntIdTable("salon_m2m_category", "salon_m2m_category_id") {
    val salonId = reference(
        "salon_id", SalonTable.id, onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val categoryId = reference(
        "category_id", CategoryTable.id, onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
}