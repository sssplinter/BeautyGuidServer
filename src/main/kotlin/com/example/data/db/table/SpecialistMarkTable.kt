package com.example.data.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table


object SpecialistMarkTable : Table("specialist_mark") {
    val userId = reference(
        "user_id", UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
     val specialistId = reference(
        "specialist_id", SpecialistTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    override  val primaryKey = PrimaryKey(specialistId, userId, name = "composite_specialist_mark_id")
    val mark = double("mark")
}