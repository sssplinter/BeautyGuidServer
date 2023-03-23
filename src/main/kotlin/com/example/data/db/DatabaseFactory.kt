package com.example.data.db

import com.example.data.db.table.*
import com.example.data.models.Articles
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)

        transaction(database) {
            SchemaUtils.create(Articles)
            SchemaUtils.create(UsersTable)
            SchemaUtils.create(UserCredentialsTable)
            SchemaUtils.create(CategoryTable)
            SchemaUtils.create(SalonMarkTable)
            SchemaUtils.create(SalonTable)
            SchemaUtils.create(SpecialistMarkTable)
            SchemaUtils.create(SpecialistTable)
            SchemaUtils.create(SalonM2MCategory)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}