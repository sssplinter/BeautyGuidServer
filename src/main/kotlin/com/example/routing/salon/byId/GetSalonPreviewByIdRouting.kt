package com.example.routing.salon.byId

import com.example.data.db.dao.SalonDao
import com.example.routing.salon.all.SalonPreview
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSalonById(
    salonDao: SalonDao
) {
    authenticate {
        get("salonPreview/{salonId}") {
            val salonId = call.parameters["salonId"]?.toIntOrNull()

            if (salonId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val salon = salonDao.fetchSalonById(salonId)
            if (salon == null) {
                call.respond(HttpStatusCode.Conflict)
                return@get
            }

            call.respond(
                status = HttpStatusCode.OK,
                message = SalonPreview(
                    salonId = salon.id,
                    salonName = salon.name,
                    salonDescription = salon.description,
                    salonPhotoUrl = salon.photoUrl,
                    location = salon.location,
                    rating = salon.rating,
                    categories = salonDao.getSalonCategories(salon.id)
                )
            )
        }
    }

}