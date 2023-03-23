package com.example.data.db.dao

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.table.UserCredentialsTable
import com.example.data.models.UserCredentials
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserCredentialsDao {

    private fun resultRowToUserCredentials(row: ResultRow) = UserCredentials(
        userId = row[UserCredentialsTable.userId].value,
        userName = row[UserCredentialsTable.username],
        password = row[UserCredentialsTable.password],
        salt = row[UserCredentialsTable.salt])

    suspend fun fetchCredentialsByUsername(username: String): UserCredentials? = dbQuery {
        UserCredentialsTable
            .select(UserCredentialsTable.username eq username)
            .map(::resultRowToUserCredentials)
            .singleOrNull()
    }

    suspend fun insertUserCredentials(
        userCredentials: UserCredentials
    ): Boolean = dbQuery {
        val insertStatement = UserCredentialsTable.insert {
            it[userId] = userCredentials.userId
            it[username] = userCredentials.userName
            it[password] = userCredentials.password
            it[salt] = userCredentials.salt
        }
        insertStatement.resultedValues?.singleOrNull() != null
    }
}
