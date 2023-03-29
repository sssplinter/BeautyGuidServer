package com.example.routing.salonNews

import com.example.data.db.dao.SalonNewsDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSalonNewsPreviewListRouting(salonNewsDao: SalonNewsDao) {
    authenticate {
        get("salonNewsPreviewList/{salonId}") {
            val salonId = call.parameters["salonId"]?.toIntOrNull()

            if (salonId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val salonNewsPreviews = salonNewsDao.getSalonNewsPreviewsList(salonId)

            call.respond(
                status = HttpStatusCode.OK,
                message = salonNewsPreviews
            )
        }
    }
}