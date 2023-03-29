package com.example.data.db.table

import org.jetbrains.exposed.dao.id.IntIdTable

object SalonTable : IntIdTable("salon", "salon_id") {
    val name = varchar("salon_name", 225)
    val photoUrl = varchar("photo_url", 225)
    val rating = double("rating")
    val location = varchar("salon_location", 500)
    val description = varchar("description", 500)
}