package com.example.data.db.dao

import com.example.data.db.DatabaseFactory
import com.example.data.db.table.CategoryTable
import com.example.data.db.table.SpecialistTable
import com.example.data.models.Category
import com.example.data.models.Specialist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class SpecialistDao {
    private fun resultRowToCategory(row: ResultRow) = Category(
        id = row[CategoryTable.id].value,
        name = row[CategoryTable.name]
    )

    // TODO
    suspend fun getSpecialistCategories(specialistId: Int): String = DatabaseFactory.dbQuery {
        (SpecialistTable innerJoin CategoryTable).select(SpecialistTable.id eq specialistId)
            .map(::resultRowToCategory).singleOrNull()?.name ?: ""
    }

    private fun resultRowToSpecialist(row: ResultRow) = Specialist(
        id = row[SpecialistTable.id].value,
        salonId = row[SpecialistTable.salonId].value,
        firstName = row[SpecialistTable.firstName],
        lastName = row[SpecialistTable.lastName],
        categoryId = row[SpecialistTable.categoryId].value,
        photoUrl = row[SpecialistTable.photoUrl],
        rating = row[SpecialistTable.rating],
        marksCount = row[SpecialistTable.marksCount]
    )

    suspend fun getAllSpecialists(): List<Specialist> = DatabaseFactory.dbQuery {
        SpecialistTable.selectAll().map(::resultRowToSpecialist)
    }

    suspend fun getSpecialistsBySalonId(salonId: Int): List<Specialist> = DatabaseFactory.dbQuery {
        SpecialistTable.select(SpecialistTable.salonId eq salonId).map(::resultRowToSpecialist)
    }
}