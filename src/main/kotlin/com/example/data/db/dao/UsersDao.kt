package com.example.data.db.dao

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.table.UsersTable
import com.example.data.models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class UsersDao {
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[UsersTable.id].value,
        firstName = row[UsersTable.firstName],
        lastName = row[UsersTable.lastName],
        mobileNumber = row[UsersTable.mobileNumber],
        email = row[UsersTable.email]
    )

    suspend fun fetchUserById(userId: Int): User? = dbQuery {
        UsersTable
            .select(UsersTable.id eq userId)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    suspend fun insertUser(user: User): User? = dbQuery {
        val insertStatement = UsersTable.insert {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[mobileNumber] = user.mobileNumber
            it[email] = user.email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    suspend fun updateUserById(user: User) = dbQuery {
        UsersTable.update({ UsersTable.id eq user.id }) {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[mobileNumber] = user.mobileNumber
            it[email] = user.email
        }
    }
}