package com.example.database

import org.jetbrains.exposed.sql.Database

data class Post(val id: Int, val title : String?, val description : String?)

class DatabaseManager {
    object DatabaseConnection {
        val db = Database.connect(
            url = "jdbc:mysql://localhost:3306/beauty_guide_db",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "newpassword;"
        )
    }
}