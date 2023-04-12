package com.example.routing.specialist

import com.example.data.db.dao.SalonDao
import com.example.data.db.dao.SpecialistDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSpecialistsBySalonIdRouting(
    specialistDao: SpecialistDao,
    salonDao: SalonDao
) {
    authenticate {
        get("specialists/{salonId}") {
            val salonId = call.parameters["salonId"]?.toIntOrNull()

            if (salonId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val specialists = specialistDao.getSpecialistsBySalonId(salonId)


            val specialistsPreviewList = specialists.map {
                SpecialistPreview(
                    id = it.id,
                    salonId = it.salonId,
                    salonName = salonDao.getSalonName(it.salonId),
                    firstName = it.firstName,
                    lastName = it.lastName,
                    category = specialistDao.getSpecialistCategories(it.id),
                    marksCount = it.marksCount,
                    rating = it.rating,
                    photoUrl = it.photoUrl
                )
            }

            call.respond(
                status = HttpStatusCode.OK,
                message = specialistsPreviewList
            )
        }
    }
}