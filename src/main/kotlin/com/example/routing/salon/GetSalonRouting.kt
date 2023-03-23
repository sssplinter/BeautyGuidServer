package com.example.routing.salon

import com.example.data.db.dao.SalonDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSalonById(
    salonDao: SalonDao
) {
    authenticate {
        get("getSalonById/{salonId}") {
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
                message = GetSalonResponse(
                    id = salon.id,
                    photoUrl = salon.photoUrl,
                    name = salon.name,
                    address = salon.description,
                    rating = salon.rating
                )
            )
        }
    }

}