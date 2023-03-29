package com.example.routing.salon.info

import com.example.data.db.dao.SalonDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSalonInfo(
    salonDao: SalonDao
) {
    authenticate {
        get("salonInfo/{salonId}") {
            val salonId = call.parameters["salonId"]?.toIntOrNull()

            if (salonId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val salonInfo = salonDao.getSalonInfoById(salonId)
            if (salonInfo == null) {
                call.respond(HttpStatusCode.Conflict)
                return@get
            }

            call.respond(
                status = HttpStatusCode.OK,
                message = salonInfo
            )
        }
    }
}