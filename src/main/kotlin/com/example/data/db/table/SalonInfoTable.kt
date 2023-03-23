package com.example.data.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SalonInfoTable : Table("salon_info") {
    val salonId = reference(
        "salon_id", SalonTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    override val primaryKey = PrimaryKey(salonId)

    val address = varchar("address", 500)
    val phoneNumber = varchar("phone_number", 20)
    val email= varchar("email", 255)
    val webLink = varchar("email", 255)
    val telegramLink = varchar("email", 255)
    val instagramLink = varchar("email", 255)
    val vkLink = varchar("email", 255)
}