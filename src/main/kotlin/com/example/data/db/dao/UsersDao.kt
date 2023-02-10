package com.example.data.db.dao

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.table.UsersTable
import com.example.data.models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UsersDao {
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[UsersTable.id].value,
        username = row[UsersTable.username],
    )

    suspend fun fetchUserByUsername(username: String): User? = dbQuery {
        UsersTable
            .select(UsersTable.username eq username)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    suspend fun insertUser(user: User) = dbQuery {
        val insertStatement = UsersTable.insert {
            it[username] = user.username
        }
        insertStatement.resultedValues?.singleOrNull()?.let {  }
    }
}