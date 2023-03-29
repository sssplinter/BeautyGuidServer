package com.example.routing.images

import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Route.getSalonNewsPhotoRouting() {
    static("salonNewsImage") {
        resources("salonNews")
    }
}