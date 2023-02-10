package com.example.data.db.dao;

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.table.UserCredentialsTable
import com.example.data.models.UserCredentials
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserCredentialsDao {

    private fun resultRowToUserCredentials(row: ResultRow) = UserCredentials(
        password = row[UserCredentialsTable.password],
        salt = row[UserCredentialsTable.salt],
    )

    suspend fun fetchCredentialsByUsername(username: String): UserCredentials? = dbQuery {
        UserCredentialsTable
            .select(UserCredentialsTable.username eq username)
            .map(::resultRowToUserCredentials)
            .singleOrNull()
    }

    //мб объединить под одну
    suspend fun insertUserCredentials(
        username: String,
        userCredentials: UserCredentials
    ): Boolean = dbQuery {
        val insertStatement = UserCredentialsTable.insert {
            it[UserCredentialsTable.username] = username
            it[UserCredentialsTable.password] = userCredentials.password
            it[UserCredentialsTable.salt] = userCredentials.salt
        }
        insertStatement.resultedValues?.singleOrNull() != null
    }
}
