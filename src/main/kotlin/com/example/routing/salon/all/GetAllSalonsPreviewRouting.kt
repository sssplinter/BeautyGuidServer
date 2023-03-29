package com.example.routing.salon.all

import com.example.data.db.dao.SalonDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllSalons(salonDao: SalonDao) {
    authenticate {
        get("allSalons") {
            val salons = salonDao.getAllSalons()

            // TODO if list is empty
            if (salons.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val salonPreviews = salons.map {
                SalonPreview(
                    salonId = it.id,
                    salonName = it.name,
                    salonDescription = it.description,
                    salonPhotoUrl = it.photoUrl,
                    location = it.location,
                    rating = it.rating,
                    categories = salonDao.getSalonCategories(it.id)
                )
            }

            call.respond(
                status = HttpStatusCode.OK,
                message = salonPreviews
            )
        }
    }
}