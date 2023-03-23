package com.example.data.db.dao

import com.example.data.db.DatabaseFactory
import com.example.data.db.table.CategoryTable
import com.example.data.db.table.SalonM2MCategoryTable
import com.example.data.db.table.SalonTable
import com.example.data.models.Category
import com.example.data.models.Salon
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class SalonDao {
    private fun resultRowToSalon(row: ResultRow) = Salon(
        id = row[SalonTable.id].value,
        photoUrl = row[SalonTable.photoUrl],
        name = row[SalonTable.name],
        description = row[SalonTable.description],
        rating = row[SalonTable.rating]
    )

    private fun resultRowToCategory(row: ResultRow) = Category(
        id = row[CategoryTable.id].value,
        name = row[CategoryTable.name]
    )

    suspend fun getAllSalons(): List<Salon> = DatabaseFactory.dbQuery {
        SalonTable.selectAll().map(::resultRowToSalon)
    }

    suspend fun getSalonCategories(salonId: Int): List<String> = DatabaseFactory.dbQuery {
        val categories =
            (SalonTable innerJoin SalonM2MCategoryTable innerJoin CategoryTable).select(SalonTable.id eq salonId)
                .map(::resultRowToCategory)
        categories.map { it.name }
    }

    suspend fun fetchSalonById(salonId: Int): Salon? = DatabaseFactory.dbQuery {
        SalonTable.select(SalonTable.id eq salonId).map(::resultRowToSalon).singleOrNull()
    }
}