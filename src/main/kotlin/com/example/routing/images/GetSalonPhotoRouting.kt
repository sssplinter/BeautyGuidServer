package com.example.routing.images

import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Route.getSalonImage() {
    static("salonImage") {
        resources("salonMainPhoto")
    }
}