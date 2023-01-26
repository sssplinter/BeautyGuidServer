package com.example.dao

import org.jetbrains.exposed.sql.Table


object Posts : Table(){
    val id = integer("id").autoIncrement()
    val title = varchar("title", 100)
    val description = varchar("description", 500)
}