package com.example.data.db.dao

import com.example.data.db.DatabaseFactory
import com.example.data.db.table.SalonNewsTable
import com.example.data.db.table.SpecialistTable
import com.example.data.models.SalonNews
import com.example.data.models.SalonNewsPreview
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select

class SalonNewsDao {

    private fun resultRowToSalonNewsPreview(row: ResultRow) = SalonNewsPreview(
        newsId = row[SalonNewsTable.id].value, photoUrl = row[SalonNewsTable.photoUrl]
    )

    private fun resultRowToSalonNews(row: ResultRow) = SalonNews(
        newsId = row[SalonNewsTable.id].value,
        specialistId = row[SalonNewsTable.specialistId].value,
        specialistFirstName = row[SpecialistTable.firstName],
        specialistLastName = row[SpecialistTable.lastName],
        photoUrl = row[SalonNewsTable.photoUrl],
        description = row[SalonNewsTable.description]
    )

    suspend fun getSalonNewsPreviewsList(salonId: Int): List<SalonNewsPreview> = DatabaseFactory.dbQuery {
        SalonNewsTable.select(SalonNewsTable.salonId eq salonId).map(::resultRowToSalonNewsPreview)
    }

    suspend fun getSalonNewsList(salonId: Int): List<SalonNews> = DatabaseFactory.dbQuery {
        (SalonNewsTable innerJoin SpecialistTable).select(SalonNewsTable.salonId eq salonId).map(::resultRowToSalonNews)
    }
}