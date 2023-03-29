package com.example.data.db.dao

import com.example.data.db.DatabaseFactory
import com.example.data.db.table.SalonNewsTable
import com.example.data.models.SalonNewsPreview
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select

class SalonNewsDao {

    private fun resultRowToSalonNewsPreview(row: ResultRow) = SalonNewsPreview(
        newsId = row[SalonNewsTable.id].value,
        photoUrl = row[SalonNewsTable.photoUrl]
    )

    suspend fun getSalonNewsPreviewsList(salonId: Int): List<SalonNewsPreview> = DatabaseFactory.dbQuery {
        SalonNewsTable.select(SalonNewsTable.salonId eq salonId).map(::resultRowToSalonNewsPreview)
    }
}